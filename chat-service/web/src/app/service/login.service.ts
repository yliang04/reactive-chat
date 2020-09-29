import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  authenticated: boolean = false;
  username: string;

  constructor(private http: HttpClient, private router: Router) { }

  authenticate(credentials, callback) {
    const headers = new HttpHeaders(credentials ? {
      authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {});

    this.http.get('user', {headers: headers}).subscribe(response => {
      this.authenticated = !!response['name'];
      this.username = response as string;
      console.log(response);

      return callback && callback();
    });
  }

  logout() {
    this.http.post('logout', {}).subscribe(response => {
      this.authenticated = false;
      this.router.navigateByUrl('/login');
    });
  }
}
