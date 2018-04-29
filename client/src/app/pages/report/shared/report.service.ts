import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppConfig } from '../../../config/app.config';
import { Observable } from 'rxjs/Observable';
import { Report } from './report.model';

@Injectable()
export class ReportService {

  private reportUrl: string;
  private headers: HttpHeaders;

  constructor(
    private http: HttpClient
  ) {
    this.reportUrl = AppConfig.API_URL + '/report';
    this.headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  }

  getList(): Observable<Report[]> {
    const url = `${this.reportUrl}/get-all`;
    return this.http.get<Report[]>(url);
  }

  findById(id: number): Observable<Report> {
    const url = `${this.reportUrl}/get/${id}`;
    return this.http.get<Report>(url);
  }

  create(report: Report): Observable<Report> {
    const url = `${this.reportUrl}`;
    return this.http.post<Report>(url, report, { headers: this.headers });
  }

  update(id: number, report: Report): Observable<Report> {
    const url = `${this.reportUrl}/${id}`;
    return this.http.post<Report>(url, report, { headers: this.headers });
  }

}
