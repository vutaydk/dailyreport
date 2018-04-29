import { Component, OnInit } from '@angular/core';
import { ProjectService } from '../../shared/project.service';
import { Project } from '../../shared/project.model';
import { Task } from '../../shared/task.model';
import { ProjectChart } from '../../shared/projectchart.model';

@Component({
  selector: 'app-project-management',
  templateUrl: './project-management.component.html',
  styleUrls: ['./project-management.component.css']
})
export class ProjectManagementComponent implements OnInit {

  projectSelected = 'all';
  taskSelected = 'all';

  projects: Project[] = [];
  tasks: Task[] = [];
  projectCharts: ProjectChart[] = [];

  constructor(
    private projectService: ProjectService
  ) { }

  ngOnInit(): void {
    this.projectService.getList().subscribe(
      res => this.projects = res
    );
    this.projectService.getListTask().subscribe(
      res => this.tasks = res
    );
    this.projectService.getListChart().subscribe(
      res => this.projectCharts = res
    );
  }

}
