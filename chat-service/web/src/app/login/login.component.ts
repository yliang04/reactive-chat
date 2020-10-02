import { Component, OnInit } from '@angular/core';
import { LoginService } from "../service/login.service";
import { HttpClient } from "@angular/common/http";
import { Router } from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  credentials = {username: '', password: ''};
  error: boolean = false;

  constructor(private login: LoginService, private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
  }

  loginUser() {
    this.login.authenticate(this.credentials, () => {
      this.router.navigateByUrl('/');
    });
    return false;
  }

}
