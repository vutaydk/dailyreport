import { NavComponent, SidebarComponent, SidebarRightComponent } from '../../shared/layout';
import { TaskAddComponent } from './task-add/task-add.component';
import { TaskEditComponent } from './task-edit/task-edit.component';
import { TaskComponent } from './task.component';
import { TaskRoutingModule } from './task.routing.module';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    TaskRoutingModule
  ],
  declarations: [
    TaskAddComponent,
    TaskEditComponent
  ]
})
export class TaskModule { }
