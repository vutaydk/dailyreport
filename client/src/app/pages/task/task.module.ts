import { NgModule } from '@angular/core';

import { SharedModule } from '../../shared/shared.module';
import { TaskRoutingModule } from './task.routing.module';

import { TaskComponent } from './component/task.component';
import { TaskAddComponent } from './component/task-add.component';
import { TaskEditComponent } from './component/task-edit.component';
import { TaskService } from './service/task.service';

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
export class TaskModule { }
