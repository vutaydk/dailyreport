import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { SidebarComponent } from './sidebar/sidebar.component';
import { RightsComponent } from './rights/rights.component';
import { NavComponent } from './nav/nav.component';
import { SidebarRightComponent } from './sidebar-right/sidebar-right.component';
import { ProjectComponent } from './project/project.component';
import { ReportComponent } from './report/report.component';
import { TaskComponent } from './task/task.component';


const appRoutes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'rights', component: RightsComponent },
  { path: 'report', component: ReportComponent },
  { path: 'project', component: ProjectComponent },
  { path: 'task', component: TaskComponent },
  //  { path: 'hero/:id',      component: HeroDetailComponent },
  //  {
  //    path: 'heroes',
  //    component: HeroListComponent,
  //    data: { title: 'Heroes List' }
  //  },
  //  { path: '',
  //    redirectTo: '/heroes',
  //    pathMatch: 'full'
  //  },
  //  { path: '**', component: PageNotFoundComponent }
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
    FormsModule,
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true } // <-- debugging purposes only
    )
  ],
  providers: [],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
