import { Injectable } from '@angular/core';
import {Observable, Subject} from "rxjs";
import {webSocket, WebSocketSubject} from "rxjs/webSocket";
import {WebsocketConfig} from "../config/websocket.config";
import {LoginService} from "./login.service";

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  private outboundChannel: WebSocketSubject<string>;
  private inboundChannel: WebSocketSubject<string>;

  constructor(private loginService: LoginService) { }

  public getOutboundChannel(): WebSocketSubject<string> {
    if(this.outboundChannel) {
      return this.outboundChannel;
    }

    let username = this.loginService.username;
    return webSocket(WebsocketConfig.OUTBOUND_CHAT_ENDPOINT + "?username=" + username);
  }

  public getInboundChannel(): WebSocketSubject<string> {
    if(this.inboundChannel) {
      return this.inboundChannel;
    }

    let username = this.loginService.username;
    return webSocket(WebsocketConfig.INBOUND_CHAT_ENDPOINT + "?username=" + username);
  }


}
