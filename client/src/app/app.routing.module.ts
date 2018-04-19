import { ProjectComponent } from './pages/project/project.component';
import { TaskComponent } from './pages/task/task.component';
import { NavComponent, SidebarComponent, SidebarRightComponent } from './shared/layout';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule, PreloadAllModules } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    loadChildren: './pages/login/login.module#LoginModule'
  },
  {
    path: 'login',
    loadChildren: './pages/login/login.module#LoginModule'
  },
  {
    path: 'project',
    loadChildren: './pages/project/project.module#ProjectModule'
  },
  {
    path: 'report',
    loadChildren: './pages/report/report.module#ReportModule'
  },
  {
    path: 'rights',
    loadChildren: './pages/rights/rights.module#RightsModule'
  },
  {
    path: 'task',
    component: TaskComponent,
    loadChildren: './pages/task/task.module#TaskModule'
  },
  {
    path: '**',
    loadChildren: './pages/page404/page404.module#Page404Module'
  }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forRoot(routes, {
      enableTracing: true, // <-- debugging purposes only
      // preload all modules; optionally we could
      // implement a custom preloading strategy for just some
      // of the modules (PRs welcome 😉)
      preloadingStrategy: PreloadAllModules
    })],
  declarations: [
    TaskComponent,
    NavComponent,
    SidebarComponent,
    SidebarRightComponent
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
