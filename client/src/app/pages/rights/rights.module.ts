import { NgModule } from '@angular/core';

import { SharedModule } from '../../shared/shared.module';
import { RightsRoutingModule } from './rights.routing.module';

import { RightsComponent } from './rights.component';
import { RightsAddComponent } from './rights-add/rights-add.component';
import { RightsEditComponent } from './rights-edit/rights-edit.component';

@NgModule({
  imports: [
    SharedModule,
    RightsRoutingModule
  ],
  declarations: [
    RightsComponent,
    RightsAddComponent,
    RightsEditComponent
  ]
})
export class RightsModule { }