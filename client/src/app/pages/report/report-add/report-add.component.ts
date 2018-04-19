import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormArray, Validators } from '@angular/forms';
import { Project } from '../../../entity/project';
import { ProjectService } from '../../../services/project.service';
import { Task } from '../../../entity/task';
import { TaskService } from '../../../services/task.service';

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
    private fb: FormBuilder,
    private projectService: ProjectService,
    private taskService: TaskService
  ) { }

  ngOnInit() {
    this.reportForm = this.fb.group({
      projectCode: ['', [Validators.required, Validators.pattern(/^[a-zA-Z]{4}$/)]],
      employeeCode: ['', [Validators.required, Validators.pattern(/^[a-zA-Z]{4}$/)]],
      tasks: this.fb.array([this.createTask()])
    });
    this.projects = this.projectService.getProjects();
    this.tasks = this.taskService.getTasks();

  }

  onSubmit() {
    console.log(JSON.stringify(this.reportForm.value));
  }

  createTask(): FormGroup {
    return this.fb.group({
      taskCode: ['', [Validators.required, Validators.pattern(/^[a-zA-Z]{4}$/)]],
      timeWork: ['', [Validators.required, Validators.pattern(/^[0-9]*$/)]],
      note: ['', [Validators.required]]
    });
  }

  addTask() {
    const control = <FormArray>this.reportForm.controls['tasks'];
    control.push(this.createTask());
  }

  remoreTask(i: number) {
    const control = <FormArray>this.reportForm.controls['tasks'];
    if (control.length > 1) {
      control.removeAt(i);
    }
  }

}
