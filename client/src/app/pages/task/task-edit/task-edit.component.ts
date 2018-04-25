import { Component, OnInit, AfterContentChecked } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TaskService } from '../task.service';
import { FormGroup } from '@angular/forms';
import { TaskInterface, Task } from '../../../interfaces/task.interface';

@Component({
  selector: 'app-task-edit',
  templateUrl: './task-edit.component.html',
  styleUrls: ['./task-edit.component.css'],
  providers: [TaskService]
})
export class TaskEditComponent implements OnInit, AfterContentChecked {
  taskForm: FormGroup;
  task: Task;
  id: number;

  constructor(
    private taskService: TaskService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.getTask();
    this.taskForm = TaskInterface.newTaskForm();
  }

  getTask() {
    this.id = +this.route.snapshot.paramMap.get('id');
    this.task = this.taskService.getTask(this.id);
    if (!this.task) {
      this.router.navigate(['404page']);
    }
  }

  ngAfterContentChecked() {
    this.getTask();
  }

  onUpdateTask(): void {
    if (this.taskForm.valid) {
      // update task
    }
  }
}
