import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { RIGHTS } from '../../services/rights-mock';
import { AppConfig } from '../../config/app.config';

@Injectable()
export class RightsService {

  private headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  private urlAPI: string = AppConfig.urlAPI;
  postUrl = 'https://reqres.in/api/users';

  constructor(
    private http: HttpClient
  ) { }

  addRights(rights): Promise<any> {
    const url = `${this.urlAPI}/rights/add`;
    return this.http.post(url, rights, { headers: this.headers }).toPromise();
  }

  updateRights(rights): Promise<any> {
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
