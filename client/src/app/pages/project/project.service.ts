import { Injectable } from '@angular/core';
import { PROJECT } from '../../services/project-mock';
import { TASK } from '../../services/task-mock';
import { PROJECT_JSON } from './project-mock';
import { Task } from '../../interfaces/task.interface';
import { Project, ProjectJSON } from '../../interfaces/project.interface';

@Injectable()
export class ProjectService {

  constructor() { }

  getProjects(): Project[] {
    return PROJECT;
  }

  getProject(id: number): Project {
    return this.getProjects().find(p => p.id === id);
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
