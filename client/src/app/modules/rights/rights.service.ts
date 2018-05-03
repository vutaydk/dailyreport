import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { AppConfig } from '../../config/app.config';
import { Observable } from 'rxjs/Observable';
import { Rights, RightsDTO } from './models/rights.model';

@Injectable()
export class RightsService {

  private readonly rightsUrl: string;

  constructor(
    private http: HttpClient
  ) {
    this.rightsUrl = AppConfig.API_URL + '/rights';
  }

  getList(): Observable<Rights[]> {
    const url = `${this.rightsUrl}/get-all`;
    return this.get<Rights[]>(url);
  }

  findById(id: number): Observable<Rights> {
    const url = `${this.rightsUrl}/get/${id}`;
    return this.get<Rights>(url);
  }

  create(rights: RightsDTO): Observable<Rights> {
    const url = `${this.rightsUrl}`;
    return this.post<Rights>(url, rights);
  }

  update(rights: RightsDTO, id: number): Observable<Rights> {
    const url = `${this.rightsUrl}/${id}`;
    return this.post<Rights>(url, rights);
  }

  private get<T>(url: string) {
    const headers = new HttpHeaders({ 'Authorization': `Bearer ${localStorage.getItem('token')}` });
    return this.http.get<T>(url, { headers: headers });
  }

  private post<T>(url: string, data: any) {
    const headers = new HttpHeaders({ 'Authorization': `Bearer ${localStorage.getItem('token')}` });
    headers.append('Content-Type', 'application/json');
    return this.http.post<T>(url, data, { headers: headers });
  }

}
