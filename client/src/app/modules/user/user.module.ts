import { NgModule } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { UserRoutingModule } from './user.routing.module';
import { UserAddComponent } from './pages/user-add/user-add.component';
import { UserEditComponent } from './pages/user-edit/user-edit.component';
import { UserComponent } from './pages/user/user.component';
import { UserService } from './user.service';

@NgModule({
  imports: [
    UserRoutingModule,
    SharedModule
  ],
  declarations: [UserAddComponent, UserEditComponent, UserComponent],

  providers: [UserService]
})
export class UserModule { }
