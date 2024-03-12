import { Injectable } from '@angular/core';
import SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';

@Injectable({
  providedIn: 'root',
})
export class WebSocketService {
  private stompClient: any;
  webSocketEndPoint = 'http://localhost:8080/ws';
//   const socket = SockJS('http://localhost:8080/ws');

  constructor() {
    const socket = SockJS('http://localhost:8080/ws');
    this.stompClient = Stomp.over(socket);
  }

  connect(): void {
    // let ws = SockJS(this.webSocketEndPoint);
    // this.stompClient = Stomp.over(ws);
    this.stompClient.connect({}, (frame: any) => {
      console.log('Connected:', frame);
    });
  }

//   disconnect(): void {
//     if (this.stompClient.connected) {
//       this.stompClient.disconnect();
//     }
//   }

//   subscribe(destination: string, callback: (message: any) => void): void {
//     if (this.stompClient.connected) {
//       this.stompClient.subscribe(destination, (message: any) => {
//         const body = JSON.parse(message.body);
//         callback(body);
//       });
//     }
//   }

//   sendMessage(destination: string, message: any): void {
//     if (this.stompClient.connected) {
//       this.stompClient.send(destination, {}, JSON.stringify(message));
//     }
//   }
}