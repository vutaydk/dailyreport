import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { SidebarComponent } from './sidebar/sidebar.component';
import { RightsComponent } from './rights/rights.component';
import { NavComponent } from './nav/nav.component';
import { SidebarRightComponent } from './sidebar-right/sidebar-right.component';
import { ProjectComponent } from './project/project.component';
import { ReportComponent } from './report/report.component';
import { TaskComponent } from './task/task.component';
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
