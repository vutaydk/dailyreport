import { NgModule } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { TaskRoutingModule } from './task.routing.module';
import { TaskComponent } from './pages/task/task.component';
import { TaskAddComponent } from './pages/task-add/task-add.component';
import { TaskEditComponent } from './pages/task-edit/task-edit.component';
import { TaskService } from './task.service';

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
