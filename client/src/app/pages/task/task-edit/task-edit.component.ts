import { Component, OnInit, AfterContentChecked } from '@angular/core';
import { Task } from '../../../entity/task';
import { TaskService } from '../../../services/task.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-task-edit',
  templateUrl: './task-edit.component.html',
  providers: [TaskService],
  styleUrls: ['./task-edit.component.css']
})
export class TaskEditComponent implements OnInit, AfterContentChecked {
  task: Task;
  id: number;

  constructor(
    private taskService: TaskService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.getTask();
  }

  getTask() {
    this.id = +this.route.snapshot.paramMap.get('id');
    this.task = this.taskService.getTask(this.id);
  }

  ngAfterContentChecked() {
    this.getTask();
  }
}
