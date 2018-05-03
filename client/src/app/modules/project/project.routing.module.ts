import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProjectComponent } from './pages/project/project.component';
import { ProjectAddComponent } from './pages/project-add/project-add.component';
import { ProjectEditComponent } from './pages/project-edit/project-edit.component';
import { ProjectManagementComponent } from './pages/project-management/project-management.component';

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
