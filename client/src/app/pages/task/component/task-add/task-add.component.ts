import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { TaskForm } from '../../shared/task.form';

@Component({
  selector: 'app-task-add',
  templateUrl: './task-add.component.html',
  styleUrls: ['./task-add.component.css']
})
export class TaskAddComponent implements OnInit {

  taskForm: FormGroup;

  ngOnInit(): void {
    this.taskForm = TaskForm.newTaskForm();
  }

  onSubmit(): void {
    if (this.taskForm.valid) {
      // add task
      console.log('added task');
    }
  }

}
