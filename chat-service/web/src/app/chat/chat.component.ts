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

  chat: string[] = []
  input: string = '';

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    this.inboundChannel = this.websocket.getInboundChannel().asObservable();
    this.inboundChannel.subscribe((event: MessageEvent) => {
      console.log(event.data);
      this.chat.push(event.data);
    });

    this.outboundChannel = this.websocket.getOutboundChannel();
    this.outboundChannel.asObservable().subscribe();
  }

  send(): void {
    this.outboundChannel.next(this.input);
    this.input = '';
  }
}
