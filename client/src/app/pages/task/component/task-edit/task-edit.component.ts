import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TaskService } from '../../shared/task.service';
import { FormGroup } from '@angular/forms';
import { TaskForm } from '../../shared/task.form';
import { Task } from '../../shared/task.model';

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
      // update task
      console.log('edited task');
    }
  }

  onRouterChange(url): void {
    const id = Number(url[0].path);
    this.taskService.findById(id).subscribe(
      res => this.task = res,
      err => { console.log(err); this.router.navigate(['404page']); }
    );
  }

}
