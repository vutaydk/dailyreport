import { NgModule } from '@angular/core';
import { Routes, RouterModule, PreloadAllModules } from '@angular/router';
import { Authenticated } from './services/authenticated.service';

const routes: Routes = [
  {
    path: '',
    canActivate: [Authenticated],
    loadChildren: './pages/login/login.module#LoginModule',
  },
  {
    path: 'login',
    loadChildren: './pages/login/login.module#LoginModule'
  },
  {
    path: 'register',
    loadChildren: './pages/register/register.module#RegisterModule'
  },
  {
    path: 'project',
    canActivate: [Authenticated],
    loadChildren: './pages/project/project.module#ProjectModule'
  },
  {
    path: 'report',
    canActivate: [Authenticated],
    loadChildren: './pages/report/report.module#ReportModule'
  },
  {
    path: 'rights',
    canActivate: [Authenticated],
    loadChildren: './pages/rights/rights.module#RightsModule'
  },
  {
    path: 'task',
    canActivate: [Authenticated],
    loadChildren: './pages/task/task.module#TaskModule'
  },
  {
    path: '404page',
    loadChildren: './pages/page404/page404.module#Page404Module'
  },
  {
    path: '**',
    redirectTo: '404page',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    enableTracing: false, // <-- debugging purposes only
    // preload all modules; optionally we could
    // implement a custom preloading strategy for just some
    // of the modules (PRs welcome 😉)
    preloadingStrategy: PreloadAllModules
  })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
