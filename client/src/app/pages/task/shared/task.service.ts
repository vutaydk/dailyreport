import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppConfig } from '../../../config/app.config';
import { Observable } from 'rxjs/Observable';
import { Task } from './task.model';

@Injectable()
export class TaskService {

  private readonly taskUrl: string;
  private headers: HttpHeaders;

  constructor(
    private http: HttpClient
  ) {
    this.taskUrl = AppConfig.API_URL + '/task';
    this.headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  }

  getList(): Observable<Task[]> {
    const url = `${this.taskUrl}/get-all`;
    return this.http.get<Task[]>(url);
  }

  findById(id: number): Observable<Task> {
    const url = `${this.taskUrl}/get/${id}`;
    return this.http.get<Task>(url);
  }

  create(task: Task): Observable<Task> {
    const url = `${this.taskUrl}`;
    return this.http.post<Task>(url, task, { headers: this.headers });
  }

  update(id: number, task: Task): Observable<Task> {
    const url = `${this.taskUrl}/${id}`;
    return this.http.post<Task>(url, task, { headers: this.headers });
  }

}
