import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ProjectInterface } from '../../../interfaces/project.interface';
import { ProjectService } from '../service/project.service';

@Component({
  selector: 'app-project-add',
  templateUrl: './project-add.component.html',
  styleUrls: ['./project-add.component.css']
})
export class ProjectAddComponent implements OnInit {
  projectForm: FormGroup;
  isSubmitting: boolean;

  constructor(
    private projectService: ProjectService
  ) { }

  ngOnInit() {
    this.projectForm = ProjectInterface.newProjectForm();
  }

  onAddProject(): void {
    if (this.projectForm.valid) {
      if (this.isSubmitting) {
        return;
      }
      this.isSubmitting = true;
      this.projectService.addProject(this.projectForm.value).subscribe(
        res => {
          console.log(res);
          this.projectForm.reset();
          this.isSubmitting = false;
        },
        err => {
          // handle error
          console.log(err);
          this.isSubmitting = false;
        }
      );
    }
  }

  get projectCode() {
    return this.projectForm.get('projectCode');
  }

  get name() {
    return this.projectForm.get('name');
  }

  get startAt() {
    return this.projectForm.get('startAt');
  }

  get finishAt() {
    return this.projectForm.get('finishAt');
  }

}
