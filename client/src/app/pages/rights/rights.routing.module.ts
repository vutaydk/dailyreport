import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RightsComponent } from './component/rights.component';
import { RightsAddComponent } from './component/rights-add.component';
import { RightsEditComponent } from './component/rights-edit.component';

const routes: Routes = [
  {
    path: '',
    component: RightsComponent,
    children: [
      {
        path: '',
        component: RightsAddComponent
      },
      {
        path: ':id',
        component: RightsEditComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RightsRoutingModule { }
