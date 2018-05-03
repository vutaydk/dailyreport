import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppConfig } from '../../config/app.config';

@Injectable()
export class AuthService {

  private loginUrl: string;
  private headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(
    private http: HttpClient
  ) {
    this.loginUrl = AppConfig.API_URL + '/login';
  }

  logout(): Promise<any> {
    const url = 'https://reqres.in/api/users';
    const usertest = {
      'email': 'peter@klaven'
    };
    return this.http.post(url, usertest, { headers: this.headers }).toPromise();
  }

  isLogged(): boolean {
    return !!localStorage.getItem('token');
  }

}
