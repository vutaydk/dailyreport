import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ProjectInterface } from '../../../interfaces/project.interface';

@Component({
  selector: 'app-project-add',
  templateUrl: './project-add.component.html',
  styleUrls: ['./project-add.component.css']
})
export class ProjectAddComponent implements OnInit {
  projectForm: FormGroup;

  constructor() { }

  ngOnInit() {
    this.projectForm = ProjectInterface.newProjectForm();
  }

  onAddProject(): void {
    if (this.projectForm.valid) {
      // add project
    }
  }
}
