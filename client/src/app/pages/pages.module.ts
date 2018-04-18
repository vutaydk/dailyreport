import { SharedModule } from '../shared/shared.module';
import {
  LoginComponent,
  RightsComponent,
  ProjectComponent,
  TaskComponent,
  ReportComponent,
  Page404Component
} from './';
import { ProjectAddComponent } from './project/project-add/project-add.component';
import { ProjectEditComponent } from './project/project-edit/project-edit.component';
import { ReportAddComponent } from './report/report-add/report-add.component';
import { RightsAddComponent } from './rights/rights-add/rights-add.component';
import { RightsEditComponent } from './rights/rights-edit/rights-edit.component';
import { TaskAddComponent } from './task/task-add/task-add.component';
import { TaskEditComponent } from './task/task-edit/task-edit.component';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

const appRoutes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  {
    path: 'rights', component: RightsComponent, children: [
      { path: '', redirectTo: 'add', pathMatch: 'full' },
      { path: 'add', component: RightsAddComponent },
      { path: 'edit/:id', component: RightsEditComponent }
    ]
  },
  { path: 'report', component: ReportComponent },
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
  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule, // shared module
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true } // <-- debugging purposes only
    )
  ],
  declarations: [
    LoginComponent,
    RightsComponent,
    ProjectComponent,
    ReportComponent,
    TaskComponent,
    Page404Component,
    ProjectAddComponent,
    ProjectEditComponent,
    TaskAddComponent,
    TaskEditComponent,
    RightsAddComponent,
    RightsEditComponent,
    ReportAddComponent
  ]
})
export class PagesModule { }
