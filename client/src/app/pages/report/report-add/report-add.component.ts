import { Component, OnInit } from '@angular/core';
import { FormGroup, FormArray } from '@angular/forms';

import { TaskService } from '../../task/task.service';
import { ProjectService } from '../../project/project.service';
import { Task } from '../../../interfaces/task.interface';
import { Project } from '../../../interfaces/project.interface';
import { ReportInterface } from '../../../interfaces/report.interface';

@Component({
  selector: 'app-report-add',
  templateUrl: './report-add.component.html',
  providers: [ProjectService, TaskService],
  styleUrls: ['./report-add.component.css']
})
export class ReportAddComponent implements OnInit {
  reportForm: FormGroup;

  projects: Project[];
  tasks: Task[];

  constructor(
    private projectService: ProjectService,
    private taskService: TaskService
  ) { }

  ngOnInit(): void {
    this.reportForm = ReportInterface.newReportForm();
    this.projectService.getProjects().subscribe(
      res => this.projects = res
    );
    this.taskService.getTasks().subscribe(
      res => this.tasks = res,
      err => console.log(err)
    );
  }

  createTask(): FormGroup {
    return ReportInterface.newTaskForm();
  }

  onAddTask(): void {
    const control = <FormArray>this.reportForm.controls['tasks'];
    control.push(this.createTask());
  }

  onRemoveTask(i: number): void {
    const control = <FormArray>this.reportForm.controls['tasks'];
    if (control.length > 1) {
      control.removeAt(i);
    }
  }

  onAddReport(): void {
    if (this.reportForm.valid) {
      // add report
    }
  }
}
