import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { TaskInterface } from '../../../interfaces/task.interface';
import { TaskService } from '../service/task.service';

@Component({
  selector: 'app-task-add',
  templateUrl: './task-add.component.html',
  styleUrls: ['./task-add.component.css']
})
export class TaskAddComponent implements OnInit {
  taskForm: FormGroup;
  isSubmitting: boolean;

  constructor(
    private taskService: TaskService
  ) { }

  ngOnInit() {
    this.taskForm = TaskInterface.newTaskForm();
    console.log(this.taskForm);
  }

  onAddTask() {
    if (this.taskForm.valid) {
      if (this.isSubmitting) {
        return;
      }
      this.isSubmitting = true;
      this.taskService.addTask(this.taskForm.value).subscribe(
        res => {
          this.taskForm.reset();
          this.isSubmitting = false;
        },
        err => {
          this.isSubmitting = false;
        }
      );
    }
  }

  get taskCode() {
    return this.taskForm.get('taskCode');
  }

  get name() {
    return this.taskForm.get('name');
  }

}
