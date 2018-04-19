import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RightsComponent } from './rights.component';
import { RightsAddComponent } from './rights-add/rights-add.component';
import { RightsEditComponent } from './rights-edit/rights-edit.component';

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
