import { AppComponent } from './app.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './pages/login/login.component';
import { TaskComponent } from './pages/task/task.component';
import { RightsComponent } from './pages/rights/rights.component';
import { ReportComponent } from './pages/report/report.component';
import { ProjectComponent } from './pages/project/project.component';

import { SidebarRightComponent } from './shared/sidebar-right/sidebar-right.component';
import { NavComponent } from './shared/nav/nav.component';
import { SidebarComponent } from './shared/sidebar/sidebar.component';

import { AuthService } from './services/auth.service';


const appRoutes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'rights', component: RightsComponent },
  { path: 'report', component: ReportComponent },
  { path: 'project', component: ProjectComponent },
  { path: 'task', component: TaskComponent }
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
    TaskComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    FormsModule
  ],
  providers: [
    AuthService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
