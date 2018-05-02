import { Component, OnInit } from '@angular/core';
import { FormGroup, FormArray } from '@angular/forms';
import { Subject } from 'rxjs/Subject';
import { debounceTime } from 'rxjs/operator/debounceTime';
import { ReportService } from '../../shared/report.service';
import { ReportForm } from '../../shared/report.form';
import { Project, Task, ReportDTO } from '../../shared/report.model';

@Component({
  selector: 'app-report-add',
  templateUrl: './report-add.component.html',
  styleUrls: ['./report-add.component.css']
})
export class ReportAddComponent implements OnInit {
  private _message = new Subject<string>();
  reportForm: FormGroup;
  projects: Project[];
  tasks: Task[];
  isSubmitting: boolean;
  message: string;
  type: string;

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
    this._message.subscribe((message) => this.message = message);
    debounceTime.call(this._message, 4000).subscribe(() => this.message = null);
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
      if (this.isSubmitting) {
        return;
      }
      this.isSubmitting = true;

      // data
      const report: ReportDTO = {
        projectId: Number(this.reportForm.get('projectCode').value),
        tasks: this.reportForm.get('tasks').value
      };

      this.reportService.create(report).subscribe(
        res => {
          console.log(res);
          this.reportForm.reset();
          this.type = 'success';
          this._message.next(`Add successfully!`);
          this.isSubmitting = false;
        },
        err => {
          console.log(err);
          this.type = 'danger';
          this._message.next(`Add fail! Please try again.`);
          this.isSubmitting = false;
        }
      );

      this.isSubmitting = false;
      console.log(report);


      // add report
    }
  }
}
