import { Component, OnInit } from '@angular/core';
import { ProjectService } from '../shared/project.service';
import { Project } from '../shared/project.model';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {

  private list: Project[];
  projects: Project[];

  constructor(
    private projectService: ProjectService
  ) { }

  ngOnInit(): void {
    this.projectService.getList().subscribe(
      res => this.list = this.projects = res,
      err => console.log(err)
    );
  }

  onSearch(event: string): void {
    const filter = event.toLowerCase().trim();
    this.projects = this.list.filter(p => p.name.toLowerCase().trim().includes(filter));
  }

}
