import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ProjectForm } from '../../shared/project.form';

@Component({
  selector: 'app-project-add',
  templateUrl: './project-add.component.html',
  styleUrls: ['./project-add.component.css']
})
export class ProjectAddComponent implements OnInit {

  projectForm: FormGroup;

  ngOnInit(): void {
    this.projectForm = ProjectForm.newProjectForm();
  }

  onSubmit(): void {
    if (this.projectForm.valid) {
      // add project
    }
  }

}
