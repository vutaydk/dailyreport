import { AppComponent } from './app.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { LoginComponent } from './pages/login/login.component';
import { TaskComponent } from './pages/task/task.component';
import { TaskAddComponent } from './pages/task/task-add/task-add.component';
import { TaskEditComponent } from './pages/task/task-edit/task-edit.component';
import { RightsComponent } from './pages/rights/rights.component';
import { RightsAddComponent } from './pages/rights/rights-add/rights-add.component';
import { RightsEditComponent } from './pages/rights/rights-edit/rights-edit.component';
import { ReportComponent } from './pages/report/report.component';
import { ProjectComponent } from './pages/project/project.component';
import { ProjectAddComponent } from './pages/project/project-add/project-add.component';
import { ProjectEditComponent } from './pages/project/project-edit/project-edit.component';
import { Page404Component } from './pages/page404/page404.component';

import {
  NavComponent,
  SidebarRightComponent,
  SidebarComponent
} from './shared/layout';

import { AuthService } from './services/auth.service';
import { ReportAddComponent } from './pages/report/report-add/report-add.component';

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
  declarations: [
    AppComponent,
    LoginComponent,
    SidebarComponent,
    RightsComponent,
    NavComponent,
    SidebarRightComponent,
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
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    NgbModule.forRoot(),
    FormsModule
  ],
  providers: [
    AuthService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
