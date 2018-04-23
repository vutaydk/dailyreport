import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ProjectComponent } from './project.component';
import { ProjectAddComponent } from './project-add/project-add.component';
import { ProjectEditComponent } from './project-edit/project-edit.component';
import { ProjectManagementComponent } from './project-management/project-management.component';

const routes: Routes = [
  {
    path: 'chart',
    component: ProjectManagementComponent
  },
  {
    path: '',
    component: ProjectComponent,
    children: [
      {
        path: '',
        component: ProjectAddComponent,
      },
      {
        path: ':id',
        component: ProjectEditComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProjectRoutingModule { }
