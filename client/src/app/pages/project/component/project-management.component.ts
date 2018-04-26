import { Component, OnInit } from '@angular/core';
import { ProjectService } from '../service/project.service';
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
    private projectService: ProjectService
  ) { }

  ngOnInit() {
    this.projectJson = this.projectService.getProjectsJson();
    this.projectService.getProjects().subscribe(
      res => this.projects = res
    );
    this.tasks = this.projectService.getTasks();
  }
}
