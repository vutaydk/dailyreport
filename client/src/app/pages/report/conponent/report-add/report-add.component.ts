import { Component, OnInit } from '@angular/core';
import { FormGroup, FormArray } from '@angular/forms';
import { ReportService } from '../../shared/report.service';
import { ReportForm } from '../../shared/report.form';
import { Project, Task } from '../../shared/report.model';

@Component({
  selector: 'app-report-add',
  templateUrl: './report-add.component.html',
  styleUrls: ['./report-add.component.css']
})
export class ReportAddComponent implements OnInit {

  reportForm: FormGroup;
  projects: Project[];
  tasks: Task[];

  constructor(
    private reportService: ReportService,
  ) { }

  ngOnInit(): void {
    this.reportForm = ReportForm.newReportForm();
    this.reportService.getListProject().subscribe(
      res => this.projects = res,
      err => console.log(err.message)
    );
    this.reportService.getListTask().subscribe(
      res => this.tasks = res,
      err => console.log(err.message)
    );
  }

  createTask(): FormGroup {
    return ReportForm.newTaskForm();
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

  onSubmit(): void {
    if (this.reportForm.valid) {
      // add report
    }
  }

}
