import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Login } from '../entity/login';

@Injectable()
export class AuthService {

  _loginUrl = '';

  login: Login = new Login();

  constructor(private _http: HttpClient) { }

  doLogin(user: Login) {
    this._http.post<Login>(this._loginUrl, this.login)
      .subscribe(data => console.table(data));
  }

}
