import { Component } from '@angular/core';
import { LoginService } from "./service/login.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'This is a chat application';

  constructor(private login: LoginService) {
    this.login.authenticate(undefined, undefined);
  }

  authenticated(): boolean {
    return this.login.authenticated;
  }

  getUsername(): string {
    return this.login.username;
  }

  logout(): void {
    this.login.logout();
  }

}
