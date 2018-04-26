import { NgModule } from '@angular/core';

import { SharedModule } from '../../shared/shared.module';
import { RightsRoutingModule } from './rights.routing.module';

import { RightsComponent } from './component/rights.component';
import { RightsAddComponent } from './component/rights-add.component';
import { RightsEditComponent } from './component/rights-edit.component';
import { RightsService } from './service/rights.service';

@NgModule({
  imports: [
    SharedModule,
    RightsRoutingModule
  ],
  declarations: [
    RightsComponent,
    RightsAddComponent,
    RightsEditComponent
  ],
  providers: [RightsService]
})
export class RightsModule { }
