import { Component, OnInit } from '@angular/core';
import { FormGroup, FormArray } from '@angular/forms';

import { TaskService } from '../../task/service/task.service';
import { ProjectService } from '../../project/service/project.service';
import { Task } from '../../../interfaces/task.interface';
import { Project } from '../../../interfaces/project.interface';
import { ReportInterface } from '../../../interfaces/report.interface';
import { ReportService } from '../service/report.service';

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
  isSubmitting: boolean;

  constructor(
    private reportService: ReportService,
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

  onAddReport(): void {
    if (this.reportForm.valid) {
      if (this.isSubmitting) {
        return;
      }
      this.isSubmitting = true;
      this.reportService.addReport(this.reportForm.value).subscribe(
        res => {
          console.log(res);
          this.reportForm.reset();
          this.isSubmitting = false;
        },
        err => {
          console.log(err);
          this.isSubmitting = false;
        }
      );
    }
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

  get projectCode() {
    return this.reportForm.get('projectCode');
  }
}
