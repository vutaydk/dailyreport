import { Component, OnInit, AfterContentChecked } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProjectService } from '../project.service';
import { Project, ProjectInterface } from '../../../interfaces/project.interface';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-project-edit',
  templateUrl: './project-edit.component.html',
  providers: [ProjectService],
  styleUrls: ['./project-edit.component.css']
})
export class ProjectEditComponent implements OnInit, AfterContentChecked {
  projectForm: FormGroup;
  project: Project;
  id: number;

  constructor(
    private projectService: ProjectService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.getProject();
    this.projectForm = ProjectInterface.newProjectForm();
  }

  getProject() {
    this.id = +this.route.snapshot.paramMap.get('id');
    this.project = this.projectService.getProject(this.id);
    if (!this.project) {
      this.router.navigate(['404page']);
    }
  }

  ngAfterContentChecked() {
    this.getProject();
  }

  onUpdateProject(): void {
    if (this.projectForm.valid) {
      // update project
    }
  }
}
