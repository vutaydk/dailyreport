import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppConfig } from '../config/app.config';

@Injectable()
export class AuthService {

  private headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(
    private http: HttpClient
  ) { }

  login(username, password): Promise<any> {
    // let url: string = `${this.urlAPI}/login`;
    const url = 'https://reqres.in/api/login';
    const usertest = {
      'email': username,
      'password': password
    };
    return this.http.post(url, usertest, { headers: this.headers }).toPromise();
  }

  logout(): Promise<any> {
    const url = 'https://reqres.in/api/users';
    const usertest = {
      'email': 'peter@klaven'
    };
    return this.http.post(url, usertest, { headers: this.headers }).toPromise();
  }

  register(user): Promise<any> {
    const url = 'https://reqres.in/api/users';
    const usertest = {
      'name': 'morpheus',
      'job': 'leader'
    };
    return this.http.post(url, usertest, { headers: this.headers }).toPromise();
  }

  is_logged_in(): Promise<any> {
    // let url: string = `${this.urlAPI}/register`;
    const url = 'https://reqres.in/api/login';
    const usertest = {
      'email': 'peter@klaven',
      'password': 'cityslicka'
    };
    return this.http.post(url, usertest, { headers: this.headers }).toPromise();
  }

}
