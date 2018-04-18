import { Component, OnInit, AfterContentChecked } from '@angular/core';
import { ProjectService } from '../../../services/project.service';
import { Project } from '../../../entity/project';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-project-edit',
  templateUrl: './project-edit.component.html',
  providers: [ProjectService],
  styleUrls: ['./project-edit.component.css']
})
export class ProjectEditComponent implements OnInit, AfterContentChecked {

  project: Project;
  id: number;

  constructor(
    private projectService: ProjectService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.getProject();
  }

  getProject() {
    this.id = +this.route.snapshot.paramMap.get('id');
    this.project = this.projectService.getProject(this.id);
  }

  onSubmit(projectForm) {
    console.log(projectForm.value);
  }

  ngAfterContentChecked() {
    this.getProject();
  }
}
