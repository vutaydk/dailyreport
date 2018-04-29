import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { TaskService } from '../../shared/task.service';
import { TaskForm } from '../../shared/task.form';
import { Task, TaskDTO } from '../../shared/task.model';

@Component({
  selector: 'app-task-edit',
  templateUrl: './task-edit.component.html',
  styleUrls: ['./task-edit.component.css']
})
export class TaskEditComponent implements OnInit {

  taskForm: FormGroup;
  task: Task;
  id: number;

  constructor(
    private taskService: TaskService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.taskForm = TaskForm.newTaskForm();
    this.route.url.subscribe(
      url => this.onRouterChange(url)
    );
  }

  onSubmit(): void {
    if (this.taskForm.valid) {
      // data
      const task:TaskDTO = {
        taskCode: this.taskForm.get('taskCode').value,
        name: this.taskForm.get('name').value
      };
      // update task
      this.taskService.update(this.id, task).subscribe(
        res => { console.log(res); this.taskForm.reset(); },
        err => console.log(err.message)
      );
    }
  }

  onRouterChange(url): void {
    const id = Number(url[0].path);
    this.taskService.findById(id).subscribe(
      res => this.task = res,
      err => { console.log(err.message); this.router.navigate(['404page']); }
    );
  }

}
