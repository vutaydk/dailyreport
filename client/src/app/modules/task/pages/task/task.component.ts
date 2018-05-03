import { Component, OnInit } from '@angular/core';
import { TaskService } from '../../task.service';
import { Task } from '../../models/task.model';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  private list: Task[];
  tasks: Task[];

  constructor(
    private taskService: TaskService
  ) { }

  ngOnInit(): void {
    this.taskService.getList().subscribe(
      res => this.list = this.tasks = res,
      err => console.log(err.message)
    );
  }

  onSearch(event: string): void {
    const filter = event.toLowerCase().trim();
    this.tasks = this.list.filter(r => r.name.trim().toLowerCase().includes(filter));
  }

}
