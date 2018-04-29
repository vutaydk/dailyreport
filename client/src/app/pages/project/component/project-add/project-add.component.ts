import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ProjectService } from '../../shared/project.service';
import { ProjectForm } from '../../shared/project.form';
import { ProjectDTO } from '../../shared/project.model';

@Component({
  selector: 'app-project-add',
  templateUrl: './project-add.component.html',
  styleUrls: ['./project-add.component.css']
})
export class ProjectAddComponent implements OnInit {

  projectForm: FormGroup;

  constructor(
    private projectService: ProjectService
  ) { }

  ngOnInit(): void {
    this.projectForm = ProjectForm.newProjectForm();
  }

  onSubmit(): void {
    if (this.projectForm.valid) {
      // data
      const project: ProjectDTO = {
        projectCode: this.projectForm.get('projectCode').value,
        name: this.projectForm.get('name').value,
        startAt: this.projectForm.get('startAt').value,
        finishAt: this.projectForm.get('finishAt').value
      };
      // create project
      this.projectService.create(project).subscribe(
        res => { console.log(res); this.projectForm.reset(); },
        err => console.log(err.message)
      );
    }
  }

}
