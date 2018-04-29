import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { LoginRoutingModule } from './login.routing.module';
import { LoginComponent } from './component/login.component';
import { CommonModule } from '@angular/common';
import { LoginService } from './shared/login.service';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    LoginRoutingModule
  ],
  declarations: [
    LoginComponent
  ],
  providers: [LoginService]
})
export class LoginModule { }
