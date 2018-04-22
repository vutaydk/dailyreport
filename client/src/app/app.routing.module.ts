import { NgModule } from '@angular/core';
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
