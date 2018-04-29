import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ProjectComponent } from './component/project.component';
import { ProjectAddComponent } from './component/project-add/project-add.component';
import { ProjectEditComponent } from './component/project-edit/project-edit.component';
import { ProjectManagementComponent } from './component/project-management/project-management.component';

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
