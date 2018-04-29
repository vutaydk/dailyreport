import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppConfig } from '../../../config/app.config';
import { Observable } from 'rxjs/Observable';
import { Report, ReportDTO, Project, Task } from './report.model';

@Injectable()
export class ReportService {

  private readonly reportUrl: string;
  private readonly projectUrl: string;
  private readonly taskUrl: string;
  private headers: HttpHeaders;

  constructor(
    private http: HttpClient
  ) {
    this.reportUrl = AppConfig.API_URL + '/report';
    this.projectUrl = AppConfig.API_URL + '/project';
    this.taskUrl = AppConfig.API_URL + '/task';
    this.headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  }

  getList(): Observable<Report[]> {
    const url = `${this.reportUrl}/get-all`;
    return this.http.get<Report[]>(url);
  }

  getListProject(): Observable<Project[]> {
    const url = `${this.projectUrl}/get-all`;
    return this.http.get<Project[]>(url);
  }

  getListTask(): Observable<Task[]> {
    const url = `${this.taskUrl}/get-all`;
    return this.http.get<Task[]>(url);
  }

  findById(id: number): Observable<Report> {
    const url = `${this.reportUrl}/get/${id}`;
    return this.http.get<Report>(url);
  }

  create(report: ReportDTO): Observable<Report> {
    const url = `${this.reportUrl}`;
    return this.http.post<Report>(url, report, { headers: this.headers });
  }

  update(id: number, report: ReportDTO): Observable<Report> {
    const url = `${this.reportUrl}/${id}`;
    return this.http.post<Report>(url, report, { headers: this.headers });
  }

}
