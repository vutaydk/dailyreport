import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { RIGHTS } from '../../../services/rights-mock';
import { AppConfig } from '../../../config/app.config';
import { Observable } from 'rxjs/Observable';
import { Rights } from '../../../interfaces/rights.interface';

@Injectable()
export class RightsService {

  private headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  private urlAPI: string = AppConfig.urlAPI;

  constructor(
    private http: HttpClient
  ) { }

  addRights(rights): Promise<any> {
    const url = `${this.urlAPI}/rights/add`;
    return this.http.post(url, rights, { headers: this.headers }).toPromise();
  }

  updateRights(rights): Promise<any> {
    console.log(rights);
    return this.http.post(this.urlAPI, rights, { headers: this.headers }).toPromise();
  }

  /*  getAllRights() {
     return RIGHTS;
   } */

  getAllRights(): Observable<Rights[]> {
    const url = `${this.urlAPI}rights.json`;
    return this.http.get<Rights[]>(url);
  }

  getRights(id: number): Observable<Rights> {
    const url = `${this.urlAPI}rights.json`;
    return this.http.get<Rights[]>(url).map(
      res => res.find(r => r.id === id)
    );
  }

  /*  getRights(id: number) {
     return this.getAllRights().find(r => r.id === id);
   } */
}
