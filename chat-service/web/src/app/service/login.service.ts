import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  authenticated: boolean = false;
  username: string = '';

  constructor(private http: HttpClient, private router: Router) { }

  authenticate(credentials, callback): void {
    const headers = new HttpHeaders(credentials ? {
      authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {});

    this.http.get('user', {headers: headers}).subscribe(response => {
      if(!response) {
        return;
      }

      this.authenticated = !!response['name'];

      if(this.authenticated) {
        this.username = response['name'];
      }

      //execute call back function if call back is not null
      return callback && callback();
    });
  }

  logout() {
    this.http.post('logout', {}).subscribe(response => {
      this.authenticated = false;
      this.username = '';
      this.router.navigateByUrl('/').finally();
    });
  }
}
