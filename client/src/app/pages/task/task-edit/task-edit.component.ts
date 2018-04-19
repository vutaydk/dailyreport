import { Component, OnInit, AfterContentChecked } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TaskService } from '../../../services/task.service';
import { Task } from '../../../entity/task';

@Component({
  selector: 'app-task-edit',
  templateUrl: './task-edit.component.html',
  styleUrls: ['./task-edit.component.css'],
  providers: [TaskService]
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
