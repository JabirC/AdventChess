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
  webSocketEndPoint = 'http://localhost:8080/ws';

  constructor() {}

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

  disconnect(mode: string): void {
    if (this.stompClient.connected) {
      this.stompClient.send("/app/disconnect", {}, mode);
      this.stompClient.disconnect();

    }
  }

  resign(reason: string): void {
    if (this.stompClient.connected) {
      this.stompClient.send("/app/disconnect", {}, reason);
    }
  }

 
  subscribe(destination: string, callback: (message: any) => void): void {
    if (this.stompClient.connected) {
      this.stompClient.subscribe(destination, (message: any) => {
        const body = JSON.parse(message.body);
        callback(body);
      });
    }
  }

  sendMessage(destination: string, message: any): void {
    if (this.stompClient.connected) {
      this.stompClient.send(destination, {}, JSON.stringify(message));
    }
  }
}