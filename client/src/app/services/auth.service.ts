import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppConfig } from '../config/app.config';

@Injectable()
export class AuthService {

  private headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  private urlAPI: string = AppConfig.urlAPI;

  constructor(
    private http: HttpClient
  ) { }

  login(user): Promise<any> {
    //let url: string = `${this.urlAPI}/login`;
    let url: string = 'https://reqres.in/api/login';
    let usertest = {
      "email": "peter@klaven",
      "password": "cityslicka"
    };
    return this.http.post(url, usertest, { headers: this.headers }).toPromise();
  }

  logout(): Promise<any> {
    let url: string = 'https://reqres.in/api/users';
    let usertest = {
      "email": "peter@klaven"
    };
    return this.http.post(url, usertest, { headers: this.headers }).toPromise();
  }

  register(user): Promise<any> {
    //let url: string = `${this.urlAPI}/register`;
    let url: string = 'https://reqres.in/api/users';
    let usertest = {
      "name": "morpheus",
      "job": "leader"
    };
    return this.http.post(url, usertest, { headers: this.headers }).toPromise();
  }

  isAuthenticated(token): Promise<any> {
    //let url: string = `${this.urlAPI}/register`;
    let url: string = 'https://reqres.in/api/login';
    let usertest = {
      "email": "peter@klaven",
      "password": "cityslicka"
    };
    return this.http.post(url, usertest, { headers: this.headers }).toPromise();
  }

}
