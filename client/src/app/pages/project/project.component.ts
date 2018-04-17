import { Component, OnInit } from '@angular/core';
import { PROJECT } from '../../services/project-mock';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {

  projects = PROJECT;

  constructor() { }

  ngOnInit() {
  }

}
