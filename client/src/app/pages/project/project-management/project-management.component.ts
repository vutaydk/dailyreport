import { Component, OnInit } from '@angular/core';
import { Project, ProjectJson } from '../../../entity/project';
import { ProjectService } from '../project.service';
import { Task, TaskJson } from '../../../entity/task';

@Component({
  selector: 'app-project-management',
  templateUrl: './project-management.component.html',
  styleUrls: ['./project-management.component.css'],
  providers: [ProjectService]
})
export class ProjectManagementComponent implements OnInit {

  projectSelected: number;
  taskSelected: number;

  projects: Project[] = [];
  tasks: Task[] = [];

  constructor(
    private _projectService: ProjectService
  ) { }

  ngOnInit() {
    this.projects = this._projectService.getProjects();
    this.tasks = this._projectService.getTasks();
  }
}