import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TaskService } from '../service/task.service';
import { FormGroup } from '@angular/forms';
import { TaskInterface, Task } from '../../../interfaces/task.interface';

@Component({
  selector: 'app-task-edit',
  templateUrl: './task-edit.component.html',
  styleUrls: ['./task-edit.component.css']
})
export class TaskEditComponent implements OnInit {
  taskForm: FormGroup;
  task: Task;
  isSubmitting: boolean;

  constructor(
    private taskService: TaskService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.taskForm = TaskInterface.newTaskForm();
    this.route.url.subscribe(
      url => this.onRouterChange(url)
    );
  }

  onUpdateTask(): void {
    if (this.taskForm.valid) {
      if (this.isSubmitting) {
        return;
      }
      this.isSubmitting = true;
      this.taskService.updateTask(this.taskForm.value).subscribe(
        res => {
          this.taskForm.reset();
          this.isSubmitting = false;
        },
        err => {
          // error handle
          this.isSubmitting = false;
        }
      );
    }
  }

  onRouterChange(url) {
    const pathParam = Number(url[0].path);
    if (!pathParam) {
      // param is not a number, redirect to 404
      this.router.navigate(['404page']);
      return;
    }
    this.taskService.getTask(pathParam).subscribe(
      res => this.task = res,
      err => this.router.navigate(['404page'])
    );
  }

  get taskCode() {
    return this.taskForm.get('taskCode');
  }

  get name() {
    return this.taskForm.get('name');
  }
}
