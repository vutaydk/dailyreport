import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { TaskComponent } from './component/task.component';
import { TaskAddComponent } from './component/task-add/task-add.component';
import { TaskEditComponent } from './component/task-edit/task-edit.component';

const routes: Routes = [
  {
    path: '',
    component: TaskComponent,
    children: [
      {
        path: '',
        component: TaskAddComponent
      },
      {
        path: ':id',
        component: TaskEditComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TaskRoutingModule { }
