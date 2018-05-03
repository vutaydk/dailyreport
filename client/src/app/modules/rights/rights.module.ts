import { NgModule } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { RightsRoutingModule } from './rights.routing.module';
import { RightsComponent } from './pages/rights/rights.component';
import { RightsAddComponent } from './pages/rights-add/rights-add.component';
import { RightsEditComponent } from './pages/rights-edit/rights-edit.component';
import { RightsService } from './rights.service';

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
