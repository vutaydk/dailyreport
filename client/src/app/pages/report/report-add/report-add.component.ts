import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormArray } from '@angular/forms';
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
  tasks: any[] = [];

  projects: Project[];
  tasksWork: Task[];

  constructor(
    private fb: FormBuilder,
    private projectService: ProjectService,
    private taskService: TaskService
  ) { }

  ngOnInit() {
    this.reportForm = this.fb.group({
      projectCode: '',
      employeeCode: '',
      tasks: this.fb.array([this.createTask()])
    });
    this.projects = this.projectService.getProjects();
    this.tasksWork = this.taskService.getTasks();
  }

  createTask(): FormGroup {
    return this.fb.group({
      taskCode: '',
      timeWork: '',
      node: ''
    });
  }

  addTask() {
    this.tasks = this.reportForm.get('tasks') as FormArray;
    this.tasks.push(this.createTask());
  }

}
