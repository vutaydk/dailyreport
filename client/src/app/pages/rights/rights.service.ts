import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { RIGHTS } from '../../services/rights-mock';

@Injectable()
export class RightsService {

  private headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  postUrl = 'https://reqres.in/api/users';

  constructor(
    private http: HttpClient
  ) { }

  addRights(rights): Promise<any> {
    console.log(rights);
    return this.http.post(this.postUrl, rights, { headers: this.headers }).toPromise();
  }

  getAllRights() {
    return RIGHTS;
  }

  getRights(id: number) {
    return this.getAllRights().find(r => r.id === id);
  }
}
