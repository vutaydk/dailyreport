import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SharedModule } from '../../shared/shared.module';

import { UserRoutingModule } from './user-routing.module';
import { UserAddComponent } from './component/user-add/user-add.component';
import { UserEditComponent } from './component/user-edit/user-edit.component';
import { UserComponent } from './component/user.component';

@NgModule({
  imports: [
    CommonModule,
    UserRoutingModule,
    SharedModule
  ],
  declarations: [UserAddComponent, UserEditComponent, UserComponent]
})
export class UserModule { }
