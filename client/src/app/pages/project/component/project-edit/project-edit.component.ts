import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProjectService } from '../../shared/project.service';
import { ProjectForm } from '../../shared/project.form';
import { Project } from '../../shared/project.model';

@Component({
  selector: 'app-project-edit',
  templateUrl: './project-edit.component.html',
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

  ngOnInit(): void {
    this.projectForm = ProjectForm.newProjectForm();
    this.route.url.subscribe(
      url => this.onRouterChange(url)
    );
  }

  onSubmit(): void {
    if (this.projectForm.valid) {
      // update project
    }
  }

  onRouterChange(url): void {
    const id = Number(url[0].path);
    this.projectService.findById(id).subscribe(
      res => this.project = res,
      err => { console.log(err); this.router.navigate(['404page']); }
    );
  }

}
