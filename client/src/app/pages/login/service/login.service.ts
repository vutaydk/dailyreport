import { Injectable } from '@angular/core';
import { AuthService } from '../../../services/auth.service';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class LoginService {

  private headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(
    private auth: AuthService,
    private http: HttpClient
  ) { }

  login(username, password): Observable<any> {
    const url = 'https://reqres.in/api/login';
    const usertest = {
      'email': 'peter@klaven',
      'password': 'cityslicka'
    };
    return this.http.post<any>(url, usertest);
  }

}
