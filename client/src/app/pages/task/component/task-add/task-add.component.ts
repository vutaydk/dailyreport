import { Component, OnInit } from '@angular/core';
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

  taskForm: FormGroup;

  constructor(
    private taskService: TaskService
  ) { }

  ngOnInit(): void {
    this.taskForm = TaskForm.newTaskForm();
  }

  onSubmit(): void {
    if (this.taskForm.valid) {
      // data
      const task: TaskDTO = {
        taskCode: this.taskForm.get('taskCode').value,
        name: this.taskForm.get('name').value
      };
      // create task
      this.taskService.create(task).subscribe(
        res => { console.log(res); this.taskForm.reset(); },
        err => console.log(err.message)
      );
    }
  }

}
