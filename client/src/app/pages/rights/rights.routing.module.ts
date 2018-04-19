import { RightsComponent, RightsAddComponent, RightsEditComponent } from './';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    component: RightsComponent
  },
  {
    path: 'add',
    component: RightsAddComponent
  },
  {
    path: 'edit/:id',
    component: RightsEditComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RightsRoutingModule { }
