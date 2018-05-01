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

  constructor(
    private http: HttpClient
  ) {
    this.reportUrl = AppConfig.API_URL + '/report';
    this.projectUrl = AppConfig.API_URL + '/project';
    this.taskUrl = AppConfig.API_URL + '/task';
  }

  getList(): Observable<Report[]> {
    const url = `${this.reportUrl}/get-all`;
    return this.get<Report[]>(url);
  }

  getListProject(): Observable<Project[]> {
    const url = `${this.projectUrl}/get-all`;
    return this.get<Project[]>(url);
  }

  getListTask(): Observable<Task[]> {
    const url = `${this.taskUrl}/get-all`;
    return this.get<Task[]>(url);
  }

  findById(id: number): Observable<Report> {
    const url = `${this.reportUrl}/get/${id}`;
    return this.get<Report>(url);
  }

  create(report: ReportDTO): Observable<Report> {
    const url = `${this.reportUrl}`;
    return this.post<Report>(url, report);
  }

  update(report: ReportDTO, id: number): Observable<Report> {
    const url = `${this.reportUrl}/${id}`;
    return this.post<Report>(url, report);
  }

  private get<T>(url: string) {
    let headers = new HttpHeaders({ 'Authorization': `Bearer ${localStorage.getItem('token')}` });
    return this.http.get<T>(url, { headers: headers });
  }

  private post<T>(url: string, data: any) {
    let headers = new HttpHeaders({ 'Authorization': `Bearer ${localStorage.getItem('token')}` });
    headers.append('Content-Type', 'application/json');
    return this.http.post<T>(url, data, { headers: headers });
  }

}
