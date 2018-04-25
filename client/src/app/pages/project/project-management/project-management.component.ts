import { Component, OnInit } from '@angular/core';
import { ProjectService } from '../project.service';
import { Task } from '../../../interfaces/task.interface';
import { ProjectJSON, Project } from '../../../interfaces/project.interface';

@Component({
  selector: 'app-project-management',
  templateUrl: './project-management.component.html',
  styleUrls: ['./project-management.component.css'],
  providers: [ProjectService]
})
export class ProjectManagementComponent implements OnInit {

  projectSelected = 'all';
  taskSelected = 'all';

  projectJson: ProjectJSON[] = [];
  projects: Project[] = [];
  tasks: Task[] = [];

  constructor(
    private _projectService: ProjectService
  ) { }

  ngOnInit() {
    this.projectJson = this._projectService.getProjectsJson();
    this.projects = this._projectService.getProjects();
    this.tasks = this._projectService.getTasks();
  }
}
