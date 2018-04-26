import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { RegisterRoutingModule } from './register.routing.module';
import { RegisterComponent } from './component/register.component';

@NgModule({
  imports: [
    FormsModule,
    RegisterRoutingModule
  ],
  declarations: [
    RegisterComponent
  ]
})
export class RegisterModule { }
