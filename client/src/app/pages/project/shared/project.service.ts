import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppConfig } from '../../../config/app.config';
import { Observable } from 'rxjs/Observable';
import { Project, ProjectDTO, ProjectDTOEdit } from './project.model';
import { Task } from './task.model';
import { ProjectChart } from './projectchart.model';

@Injectable()
export class ProjectService {

  private readonly projectUrl: string;
  private readonly taskUrl: string;

  constructor(
    private http: HttpClient
  ) {
    this.projectUrl = AppConfig.API_URL + '/project';
    this.taskUrl = AppConfig.API_URL + '/task';
  }

  getList(): Observable<Project[]> {
    const url = `${this.projectUrl}/get-all`;
    return this.get<Project[]>(url);
  }

  getListChart(): Observable<ProjectChart[]> {
    const url = `${this.projectUrl}/get-chart`;
    return this.get<ProjectChart[]>(url);
  }

  findById(id: number): Observable<Project> {
    const url = `${this.projectUrl}/get/${id}`;
    return this.get<Project>(url);
  }

  create(project: ProjectDTO): Observable<Project> {
    const url = `${this.projectUrl}`;
    return this.post<Project>(url, project);
  }

  update(project: ProjectDTOEdit, id: number): Observable<Project> {
    const url = `${this.projectUrl}/${id}`;
    return this.post<Project>(url, project);
  }

  getListTask(): Observable<Task[]> {
    const url = `${this.taskUrl}/get-all`;
    return this.get<Task[]>(url);
  }

  findTaskById(id: number): Observable<Task> {
    const url = `${this.taskUrl}/get/${id}`;
    return this.get<Task>(url);
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
