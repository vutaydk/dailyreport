import { NgModule } from '@angular/core';

import { SharedModule } from '../../shared/shared.module';
import { UserRoutingModule } from './user-routing.module';
import { UserAddComponent } from './component/user-add/user-add.component';
import { UserEditComponent } from './component/user-edit/user-edit.component';
import { UserComponent } from './component/user.component';
import { UserService } from './shared/user.service';

@NgModule({
  imports: [
    UserRoutingModule,
    SharedModule
  ],
  declarations: [UserAddComponent, UserEditComponent, UserComponent],

  providers: [UserService]
})
export class UserModule { }
