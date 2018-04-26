import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Report } from '../../../interfaces/report.interface';
import { AppConfig } from '../../../config/app.config';

@Injectable()
export class ReportService {

  private headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  private urlAPI: string = AppConfig.urlAPI;

  constructor(
    private http: HttpClient
  ) { }

  getReport(): void {

  }

  getReports(): Observable<Report[]> {
    const url = `${this.urlAPI}report.json`;
    return this.http.get<Report[]>(url);
  }
}
