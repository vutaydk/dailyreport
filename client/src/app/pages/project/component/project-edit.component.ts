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
  id: number;

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
      // update project
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
}
