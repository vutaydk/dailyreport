import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProjectService } from '../service/project.service';
import { Project, ProjectInterface } from '../../../interfaces/project.interface';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-project-edit',
  templateUrl: './project-edit.component.html',
  providers: [ProjectService],
  styleUrls: ['./project-edit.component.css']
})
export class ProjectEditComponent implements OnInit {
  projectForm: FormGroup;
  project: Project;
  isSubmitting: boolean;

  constructor(
    private projectService: ProjectService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.projectForm = ProjectInterface.newProjectForm();
    this.route.url.subscribe(
      url => this.onRouterChange(url)
    );
  }

  onUpdateProject(): void {
    if (this.projectForm.valid) {
      if (this.isSubmitting) {
        return;
      }
      this.isSubmitting = true;
      this.projectService.updateProject(this.projectForm.value).subscribe(
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

  onRouterChange(url) {
    const pathParam = Number(url[0].path);
    if (!pathParam) {
      // param is not a number, redirect to 404
      this.router.navigate(['404page']);
      return;
    }
    this.projectService.getProject(pathParam).subscribe(
      res => this.project = res,
      err => this.router.navigate(['404page'])
    );
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
