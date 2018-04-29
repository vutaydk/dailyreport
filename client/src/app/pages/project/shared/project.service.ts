import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppConfig } from '../../../config/app.config';
import { Observable } from 'rxjs/Observable';
import { Project } from './project.model';
import { Task } from './task.model';
import { ProjectChart } from './projectchart.model';

@Injectable()
export class ProjectService {

  private projectUrl: string;
  private taskUrl: string;
  private headers: HttpHeaders;

  constructor(
    private http: HttpClient
  ) {
    this.projectUrl = AppConfig.API_URL + '/project';
    this.taskUrl = AppConfig.API_URL + '/task';
    this.headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  }

  getList(): Observable<Project[]> {
    const url = `${this.projectUrl}/get-all`;
    return this.http.get<Project[]>(url);
  }

  getListChart(): Observable<ProjectChart[]> {
    const url = `${this.projectUrl}/get-chart`;
    return this.http.get<ProjectChart[]>(url);
  }

  findById(id: number): Observable<Project> {
    const url = `${this.projectUrl}/get/${id}`;
    return this.http.get<Project>(url);
  }

  create(project: Project): Observable<Project> {
    const url = `${this.projectUrl}`;
    return this.http.post<Project>(url, project, { headers: this.headers });
  }

  update(id: number, project: Project): Observable<Project> {
    const url = `${this.projectUrl}/${id}`;
    return this.http.post<Project>(url, project, { headers: this.headers });
  }

  getListTask(): Observable<Task[]> {
    const url = `${this.taskUrl}/get-all`;
    return this.http.get<Task[]>(url);
  }

  findTaskById(id: number): Observable<Task> {
    const url = `${this.taskUrl}/get/${id}`;
    return this.http.get<Task>(url);
  }

}
