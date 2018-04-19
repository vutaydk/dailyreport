import { RightsComponent } from './';
import { RightsRoutingModule } from './rights.routing.module';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';


@NgModule({
  imports: [
    FormsModule,
    RightsRoutingModule
  ],
  declarations: [
    RightsComponent
  ]
})
export class RightsModule { }
