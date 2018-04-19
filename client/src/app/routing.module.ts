import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {
  Page404Component,
  TaskComponent,
  ProjectComponent,
  ReportComponent,
  RightsComponent,
  LoginComponent
} from './pages';
import { TaskAddComponent } from './pages/task/task-add/task-add.component';
import { TaskEditComponent } from './pages/task/task-edit/task-edit.component';
import { ProjectEditComponent } from './pages/project/project-edit/project-edit.component';
import { ProjectAddComponent } from './pages/project/project-add/project-add.component';
import { RightsEditComponent } from './pages/rights/rights-edit/rights-edit.component';
import { RightsAddComponent } from './pages/rights/rights-add/rights-add.component';
import { ReportAddComponent } from './pages/report/report-add/report-add.component';
import { ReportEditComponent } from './pages/report/report-edit/report-edit.component';
import { ReportManagermentComponent } from './pages/report/report-managerment/report-managerment.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  {
    path: 'rights', component: RightsComponent, children: [
      { path: '', redirectTo: 'add', pathMatch: 'full' },
      { path: 'add', component: RightsAddComponent },
      { path: 'edit/:id', component: RightsEditComponent }
    ]
  },
  {
    path: 'report', component: ReportComponent, children: [
      { path: '', redirectTo: 'add', pathMatch: 'full' },
      { path: 'add', component: ReportAddComponent },
      { path: 'managerment', component: ReportManagermentComponent },
      { path: 'edit/:id', component: ReportEditComponent }
    ]
  },
  {
    path: 'project', component: ProjectComponent, children: [
      { path: '', redirectTo: 'add', pathMatch: 'full' },
      { path: 'add', component: ProjectAddComponent },
      { path: 'edit/:id', component: ProjectEditComponent }
    ]
  },
  {
    path: 'task', component: TaskComponent, children: [
      { path: '', redirectTo: 'add', pathMatch: 'full' },
      { path: 'add', component: TaskAddComponent },
      { path: 'edit/:id', component: TaskEditComponent }
    ]
  },
  { path: '**', component: Page404Component }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: []
})
export class AppRoutingModule {
}
