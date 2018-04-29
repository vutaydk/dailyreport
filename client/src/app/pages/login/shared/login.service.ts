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
    // this.headers.append('Authorization', 'Bearer hanh');
  }

  login(login: Login): Observable<any> {
    const url = `${this.loginUrl}`;
    const out = this.http.post<any>(url, login, { headers: this.headers });

    let isLogged = false;
    this.http.get<any>(url, { headers: this.headers }).subscribe(
      res => { console.log(res); isLogged = true; },
      err => console.log(err)
    );

    return out;
  }

}
