import { Component, OnInit } from '@angular/core';
import { Task } from '../../entity/task';
import { TaskService } from '../../services/task.service';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css'],
  providers: [TaskService]
})
export class TaskComponent implements OnInit {

  tasks: Task[];

  constructor(
    private taskService: TaskService
  ) { }

  ngOnInit() {
    this.tasks = this.taskService.getTasks();
  }

}
