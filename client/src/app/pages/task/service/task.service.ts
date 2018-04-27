import { Injectable } from '@angular/core';
import { TASK } from '../../../services/task-mock';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Task } from '../../../interfaces/task.interface';
import { AppConfig } from '../../../config/app.config';
import 'rxjs/add/operator/map';

@Injectable()
export class TaskService {

  private headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  private urlAPI: string = AppConfig.urlAPI;

  constructor(
    private http: HttpClient
  ) { }

  getTasks(): Observable<Task[]> {
    const url = `${this.urlAPI}task.json`;
    return this.http.get<Task[]>(url);
  }

  getTask(id: number): Observable<Task> {
    const url = `${this.urlAPI}task.json`;
    return this.http.get<Task[]>(url).map(
      res => res.find(t => t.id === id)
    );
  }

  addTask(task: Task): Observable<Task> {
    // const url = `${this.urlAPI}task.json`;
    const url = 'https://reqres.in/api/users';
    return this.http.post<Task>(url, task);
  }

  updateTask(task: Task): Observable<Task> {
    // const url = `${this.urlAPI}task.json`;
    const url = 'https://reqres.in/api/users';
    return this.http.post<Task>(url, task);
  }
}
