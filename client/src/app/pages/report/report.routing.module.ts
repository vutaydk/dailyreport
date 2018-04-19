import { ReportManagermentComponent, ReportEditComponent, ReportComponent } from './';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    component: ReportComponent
  },
  {
    path: 'managerment',
    component: ReportManagermentComponent
  },
  {
    path: 'edit/:id',
    component: ReportEditComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReportRoutingModule { }
