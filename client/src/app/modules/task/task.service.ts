import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppConfig } from '../../config/app.config';
import { Observable } from 'rxjs/Observable';
import { Task, TaskDTO, TaskDTOEdit } from './models/task.model';

@Injectable()
export class TaskService {

  private readonly taskUrl: string;

  constructor(
    private http: HttpClient
  ) {
    this.taskUrl = `${AppConfig.API_URL}/task`;
  }

  getList(): Observable<Task[]> {
    const url = `${this.taskUrl}/get-all`;
    return this.get<Task[]>(url);
  }

  findById(id: number): Observable<Task> {
    const url = `${this.taskUrl}/get/${id}`;
    return this.get<Task>(url);
  }

  create(task: TaskDTO): Observable<Task> {
    const url = `${this.taskUrl}`;
    return this.post<Task>(url, task);
  }

  update(task: TaskDTOEdit, id: number): Observable<Task> {
    const url = `${this.taskUrl}/${id}`;
    return this.post<Task>(url, task);
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
