import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { AppConfig } from '../../../config/app.config';
import { Login } from './login.model';

@Injectable()
export class LoginService {

  private loginUrl: string;
  private headers: HttpHeaders;

  constructor(
    private http: HttpClient
  ) {
    this.loginUrl = AppConfig.API_URL + '/login';
    this.headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  }

  login(username, password): Observable<any> {
    const url = `${this.loginUrl}`;
    const usertest: Login = {
      employeeCode: username,
      password: password
    };
    localStorage.setItem('token', 'asdasd');
    return this.http.post<any>(url, usertest, { headers: this.headers });
  }

}
