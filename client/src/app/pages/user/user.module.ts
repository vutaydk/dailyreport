import { NgModule } from '@angular/core';

import { SharedModule } from '../../shared/shared.module';
import { TaskRoutingModule } from './task.routing.module';

import { TaskComponent } from './component/task.component';
import { TaskAddComponent } from './component/task-add/task-add.component';
import { TaskEditComponent } from './component/task-edit/task-edit.component';
import { TaskService } from './shared/task.service';

@NgModule({
  imports: [
    SharedModule,
    TaskRoutingModule
  ],
  declarations: [
    TaskComponent,
    TaskAddComponent,
    TaskEditComponent
  ],
  providers: [TaskService]
})
export class UserModule { }
