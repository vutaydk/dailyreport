import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs/Subject';
import { debounceTime } from 'rxjs/operator/debounceTime';
import { TaskService } from '../../shared/task.service';
import { TaskForm } from '../../shared/task.form';
import { TaskDTO } from '../../shared/task.model';

@Component({
  selector: 'app-task-edit',
  templateUrl: './task-edit.component.html',
  styleUrls: ['./task-edit.component.css']
})
export class TaskEditComponent implements OnInit {
  private _message = new Subject<string>();
  taskForm: FormGroup;
  isSubmitting: boolean;
  id: number;
  message: string;
  type: string;

  constructor(
    private taskService: TaskService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.route.url.subscribe(
      url => this.onRouterChange(url)
    );
    this.taskForm = TaskForm.newTaskForm();
    this._message.subscribe((message) => this.message = message);
    debounceTime.call(this._message, 4000).subscribe(() => this.message = null);
  }

  onRouterChange(url): void {
    const id = Number(url[0].path);
    this.taskService.findById(id).subscribe(
      res => this.taskForm = TaskForm.newTaskForm(res),
      err => { console.log(err.message); this.router.navigate(['404page']); }
    );
  }

  onSubmit(): void {
    if (this.taskForm.valid) {
      if (this.isSubmitting) {
        return;
      }
      this.isSubmitting = true;
      // data
      const task: TaskDTO = {
        taskCode: this.taskForm.get('taskCode').value,
        name: this.taskForm.get('name').value
      };
      // update task
      this.taskService.update(this.id, task).subscribe(
        res => {
          console.log(res);
          this.taskForm.reset();
          this.type = 'success';
          this._message.next(`Update successfully!`);
          this.isSubmitting = false;
        },
        err => {
          console.log(err.message);
          this.type = 'danger';
          this._message.next(`Update fail! Please try again.`);
          this.isSubmitting = false;
        }
      );
    }
  }

}
