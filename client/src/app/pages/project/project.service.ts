import { Injectable } from '@angular/core';
import { PROJECT } from '../../services/project-mock';
import { TASK } from '../../services/task-mock';
import { PROJECT_JSON } from './project-mock';
import { Task } from '../../interfaces/task.interface';
import { Project, ProjectJSON } from '../../interfaces/project.interface';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class ProjectService {

  private headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  // private urlAPI: string = AppConfig.urlAPI;
  private urlAPI = 'https://raw.githubusercontent.com/vutaydk/dailyreport/dev-client/client/src/app/services';

  constructor(
    private http: HttpClient
  ) { }

  getProjects(): Observable<Project[]> {
    const url = `${this.urlAPI}/project.json`;
    return this.http.get<Project[]>(url);
  }

  getProject(id: number): Observable<Project> {
    const url = `${this.urlAPI}/project.json`;
    return this.http.get<Project[]>(url).map(
      res => res.find(p => p.id === id)
    );
  }

  getProjectsJson(): ProjectJSON[] {
    return PROJECT_JSON;
  }

  getProjectJson(id: number): ProjectJSON {
    return this.getProjectsJson().find(p => p.id === id);
  }


  getTasks(): Task[] {
    return TASK;
  }

  getTask(id: number): Task {
    return this.getTasks().find(t => t.id === id);
  }


}
