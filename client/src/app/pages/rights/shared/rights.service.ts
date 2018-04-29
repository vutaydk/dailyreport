import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { AppConfig } from '../../../config/app.config';
import { Observable } from 'rxjs/Observable';
import { Rights, RightsDTO } from './rights.model';

@Injectable()
export class RightsService {

  private readonly rightsUrl: string;
  private headers: HttpHeaders;

  constructor(
    private http: HttpClient
  ) {
    this.rightsUrl = AppConfig.API_URL + '/rights';
    this.headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  }

  getList(): Observable<Rights[]> {
    const url = `${this.rightsUrl}/get-all`;
    return this.http.get<Rights[]>(url);
  }

  findById(id: number): Observable<Rights> {
    const url = `${this.rightsUrl}/get/${id}`;
    return this.http.get<Rights>(url);
  }

  create(rights: RightsDTO): Observable<Rights> {
    const url = `${this.rightsUrl}`;
    return this.http.post<Rights>(url, rights, { headers: this.headers });
  }

  update(id: number, rights: RightsDTO): Observable<Rights> {
    const url = `${this.rightsUrl}/${id}`;
    return this.http.post<Rights>(url, rights, { headers: this.headers });
  }

}
