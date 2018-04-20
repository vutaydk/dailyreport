import { NgModule } from '@angular/core';

import { SharedModule } from '../../shared/shared.module';
import { ProjectRoutingModule } from './project.routing.module';

import { ProjectComponent } from './project.component';
import { ProjectAddComponent } from './project-add/project-add.component';
import { ProjectEditComponent } from './project-edit/project-edit.component';

@NgModule({
  imports: [
    SharedModule,
    ProjectRoutingModule
  ],
  declarations: [
    ProjectComponent,
    ProjectAddComponent,
    ProjectEditComponent
  ]
})
export class ProjectModule { }
