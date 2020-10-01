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
  private inboundChannel: WebSocketSubject<MessageEvent>;

  constructor(private loginService: LoginService) { }

  public getOutboundChannel(): WebSocketSubject<any> {
    if(this.outboundChannel) {
      return this.outboundChannel;
    }

    let username = this.loginService.username;
    return webSocket(WebsocketConfig.OUTBOUND_CHAT_ENDPOINT + "?username=" + username);
  }

  /**
   * The data string I'm getting back looks like this: "(yiyang)(all): Hello world"
   * WebsocketSubject by default will apply JSON.parse() to deserialize message data. Because of this I'm getting a
   * json parsing error. To work around this I added a custom deserializer to ignore JSON parsing.
   *
   * TODO: See if there is a way to avoid using this custom deserializer.
   */
  public getInboundChannel(): WebSocketSubject<MessageEvent> {
    if(this.inboundChannel) {
      return this.inboundChannel;
    }

    let username = this.loginService.username;
    return webSocket({url: WebsocketConfig.INBOUND_CHAT_ENDPOINT + "?username=" + username, deserializer: msg => msg});
  }


}
