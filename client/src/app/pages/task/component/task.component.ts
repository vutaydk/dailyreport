import { Component, OnInit } from '@angular/core';
import { TaskService } from '../service/task.service';
import { Task } from '../../../interfaces/task.interface';

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
    this.taskService.getTasks().subscribe(
      res => {
        this.tasks = res;
        console.log(res);
      },
      err => console.log(err.message)
    );
  }

  onSearch(event: string) {
    const filter = event.toLowerCase().trim();
    this.taskService.getTasks().subscribe(
      res => this.tasks = res.filter(r => r.name.trim().toLowerCase().includes(filter))
    );
  }

}
