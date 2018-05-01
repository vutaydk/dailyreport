import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs/Subject';
import { debounceTime } from 'rxjs/operator/debounceTime';
import { FormGroup } from '@angular/forms';
import { TaskService } from '../../shared/task.service';
import { TaskForm } from '../../shared/task.form';
import { Task, TaskDTO } from '../../shared/task.model';

@Component({
  selector: 'app-task-add',
  templateUrl: './task-add.component.html',
  styleUrls: ['./task-add.component.css']
})
export class TaskAddComponent implements OnInit {
  private _message = new Subject<string>();
  taskForm: FormGroup;
  isSubmitting: boolean;
  message: string;
  type: string;

  constructor(
    private taskService: TaskService
  ) { }

  ngOnInit(): void {
    this.taskForm = TaskForm.newTaskForm();
    this._message.subscribe((message) => this.message = message);
    debounceTime.call(this._message, 4000).subscribe(() => this.message = null);
  }

  onSubmit(): void {
    if (this.taskForm.valid) {
      if (this.isSubmitting) {
        return;
      }
      this.isSubmitting = true;
      // data
      const task: TaskDTO = {
        taskCode: this.taskForm.get('taskCode').value,
        name: this.taskForm.get('name').value
      };
      // create task
      this.taskService.create(task).subscribe(
        res => {
          console.log(res);
          this.taskForm.reset();
          this.type = 'success';
          this._message.next(`Add successfully!`);
          this.isSubmitting = false;
        },
        err => {
          console.log(err.message);
          this.type = 'danger';
          this._message.next(`Add fail! Please try again.`);
          this.isSubmitting = false;
        }
      );
    }
  }
}
