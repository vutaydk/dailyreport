import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Subject } from 'rxjs/Subject';
import { debounceTime } from 'rxjs/operator/debounceTime';
import { ProjectService } from '../../shared/project.service';
import { ProjectForm } from '../../shared/project.form';
import { ProjectDTO } from '../../shared/project.model';

@Component({
  selector: 'app-project-add',
  templateUrl: './project-add.component.html',
  styleUrls: ['./project-add.component.css']
})
export class ProjectAddComponent implements OnInit {
  private _message = new Subject<string>();
  projectForm: FormGroup;
  isSubmitting: boolean;
  message: string;
  type: string;

  constructor(
    private projectService: ProjectService
  ) { }

  ngOnInit(): void {
    this.projectForm = ProjectForm.newProjectForm();
    this._message.subscribe((message) => this.message = message);
    debounceTime.call(this._message, 4000).subscribe(() => this.message = null);
  }

  onSubmit(): void {
    if (this.projectForm.valid) {
      if (this.isSubmitting) {
        return;
      }
      this.isSubmitting = true;
      // data
      const project: ProjectDTO = {
        projectCode: this.projectForm.get('projectCode').value,
        name: this.projectForm.get('name').value,
        startAt: this.projectForm.get('startAt').value,
        finishAt: this.projectForm.get('finishAt').value
      };
      // create project
      this.projectService.create(project).subscribe(
        res => {
          console.log(res);
          this.projectForm.reset();
          this.type = 'success';
          this._message.next(`Add successfully!`);
          this.isSubmitting = false;
        },
        err => {
          console.log(err.message);
          this.type = 'danger';
          this._message.next(`Add fail! Please try again.`);
          this.isSubmitting = false;
        }
      );
    }
  }

}
