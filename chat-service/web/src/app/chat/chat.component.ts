import {AfterViewInit, Component, Input, OnInit} from '@angular/core';
import {WebsocketService} from "../service/websocket.service";
import {LoginService} from "../service/login.service";
import {Observable} from "rxjs";
import {WebSocketSubject} from "rxjs/webSocket";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent implements OnInit, AfterViewInit {

  constructor(private websocket: WebsocketService) { }
  private inboundChannel: Observable<MessageEvent>;
  private outboundChannel: WebSocketSubject<string>;

  chat: string = '';

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    this.inboundChannel = this.websocket.getInboundChannel().asObservable();
    this.inboundChannel.subscribe((event: MessageEvent) => {
      console.log(event.data);
      this.chat += event.data + '\n';
    });

    this.outboundChannel = this.websocket.getOutboundChannel();
    this.outboundChannel.asObservable().subscribe();
  }

  send(message: string): void {
    console.log("send message: " + message);
    this.outboundChannel.next(message);
  }

}
