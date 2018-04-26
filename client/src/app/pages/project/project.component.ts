import { Component, OnInit } from '@angular/core';

import { PROJECT } from '../../services/project-mock';
import { ProjectService } from './project.service';
import { Project } from '../../interfaces/project.interface';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css'],
  providers: [ProjectService]
})
export class ProjectComponent implements OnInit {
  projects: Project[];

  constructor(
    private projectService: ProjectService
  ) { }

  ngOnInit() {
    this.projectService.getProjects().subscribe(
      res => this.projects = res,
      err => console.log(err)
    );
  }

  onSearch(event: string) {
    const filter = event.toLowerCase().trim();
    this.projectService.getProjects().subscribe(
      res => this.projects = res.filter(p => p.name.toLowerCase().trim().includes(filter))
    );
  }

}
