import { NgModule } from '@angular/core';
import { Routes, RouterModule, PreloadAllModules } from '@angular/router';
import { Authenticated } from './shared/services/authenticated';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    loadChildren: './modules/login/login.module#LoginModule'
  },
  {
    path: 'project',
    canActivate: [Authenticated],
    loadChildren: './modules/project/project.module#ProjectModule'
  },
  {
    path: 'report',
    canActivate: [Authenticated],
    loadChildren: './modules/report/report.module#ReportModule'
  },
  {
    path: 'rights',
    canActivate: [Authenticated],
    loadChildren: './modules/rights/rights.module#RightsModule'
  },
  {
    path: 'task',
    canActivate: [Authenticated],
    loadChildren: './modules/task/task.module#TaskModule'
  },
  {
    path: 'user',
    canActivate: [Authenticated],
    loadChildren: './modules/user/user.module#UserModule'
  },
  {
    path: '404page',
    loadChildren: './modules/page404/page404.module#Page404Module'
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
