import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { TaskInterface } from '../../../interfaces/task.interface';

@Component({
  selector: 'app-task-add',
  templateUrl: './task-add.component.html',
  styleUrls: ['./task-add.component.css']
})
export class TaskAddComponent implements OnInit {
  taskForm: FormGroup;

  constructor() { }

  ngOnInit() {
    this.taskForm = TaskInterface.newTaskForm();
  }

  onAddTask() {
    if (this.taskForm.valid) {
      // add task
    }
  }

}
