import { Injectable } from '@angular/core';
import { PROJECT } from '../../services/project-mock';
import { TASK } from '../../services/task-mock';
import { Project, ProjectJson } from '../../entity/project';
import { Task } from '../../entity/task';
import { PROJECT_JSON } from './project-mock';

@Injectable()
export class ProjectService {

  constructor() { }

  getProjects(): Project[] {
    return PROJECT;
  }

  getProject(id: number): Project {
    return this.getProjects().find(p => p.id === id);
  }

  getProjectsJson(): ProjectJson[] {
    return PROJECT_JSON;
  }

  getProjectJson(id: number): ProjectJson {
    return this.getProjectsJson().find(p => p.id === id);
  }


  getTasks(): Task[] {
    return TASK;
  }

  getTask(id: number): Task {
    return this.getTasks().find(t => t.id === id);
  }


}
