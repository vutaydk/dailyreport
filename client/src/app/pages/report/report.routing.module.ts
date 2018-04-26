import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ReportComponent } from './conponent/report.component';
import { ReportAddComponent } from './conponent/report-add.component';
import { ReportEditComponent } from './conponent/report-edit.component';
import { ReportManagermentComponent } from './conponent/report-managerment.component';

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
