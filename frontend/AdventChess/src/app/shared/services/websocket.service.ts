import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';

@Injectable({
  providedIn: 'root',
})
export class WebSocketService {
  private stompClient: any;
  webSocketEndPoint = 'http://localhost:8080/wss';

  constructor() {}

  /**
   * Establishes a WebSocket connection to the specified endpoint.
   * Upon successful connection, resolves a promise with the username retrieved from the connection frame headers.
   * @returns A promise with the username of the connected user.
   */
  connect(): Promise<string> {
    const socket = new SockJS(this.webSocketEndPoint);
    this.stompClient = Stomp.over(socket);
    return new Promise<string>((resolve, reject) => {
        this.stompClient.connect({}, (frame: any) => {
        console.log('Connected:', frame);
        let user = frame.headers['user-name'];
        resolve(user);
        });
    });
  }

  /**
   * Disconnects the WebSocket connection.
   * @param mode The mode of disconnection (e.g., "disconnect", "timeout").
   */
  disconnect(mode: string): void {
    if (this.stompClient.connected) {
      this.stompClient.send("/app/disconnect", {}, mode);
      this.stompClient.disconnect();

    }
  }

  /**
   * Resigns from a game by sending a resignation message over the WebSocket connection.
   * @param reason The reason for resignation.
   */
  resign(reason: string): void {
    if (this.stompClient.connected) {
      this.stompClient.send("/app/disconnect", {}, reason);
    }
  }

 
  /**
   * Subscribes to a specific destination on the WebSocket server to receive messages.
   * @param destination The destination to subscribe to (e.g., a topic or queue).
   * @param callback The callback function to be invoked when a message is received.
   */
  subscribe(destination: string, callback: (message: any) => void): void {
    if (this.stompClient.connected) {
      this.stompClient.subscribe(destination, (message: any) => {
        const body = JSON.parse(message.body);
        callback(body);
      });
    }
  }

  /**
   * Sends a message to a specific destination on the WebSocket server.
   * @param destination The destination where to send the message.
   * @param message The message to be sent, converted to JSON format.
   */
  sendMessage(destination: string, message: any): void {
    if (this.stompClient.connected) {
      this.stompClient.send(destination, {}, JSON.stringify(message));
    }
  }
}