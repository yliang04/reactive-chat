import {AfterViewInit, Component, Input, OnInit} from '@angular/core';
import {WebsocketService} from "../service/websocket.service";
import {LoginService} from "../service/login.service";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent implements OnInit, AfterViewInit {

  constructor(private websocket: WebsocketService) { }
  private inboundChannel;
  private outboundChannel;

  chat: string = '';

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    this.inboundChannel = this.websocket.getInboundChannel();
    this.inboundChannel.subscribe((message: string) => {
      this.chat += message + '\n';
    });

    this.outboundChannel = this.websocket.getOutboundChannel();
  }

  send(message: string): void {
    this.outboundChannel.next(message);
  }

}
