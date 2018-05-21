import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ReportComponent } from './pages/report/report.component';
import { ReportAddComponent } from './pages/report-add/report-add.component';
import { ReportEditComponent } from './pages/report-edit/report-edit.component';
import { ReportManagermentComponent } from './pages/report-mangement/report-managerment.component';

const routes: Routes = [
  {
    path: '',
    component: ReportComponent,
    children: [
      {
        path: '',
        component: ReportAddComponent
      },
      {
        path: 'management',
        component: ReportManagermentComponent
      },
      {
        path: ':id',
        component: ReportEditComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReportRoutingModule { }