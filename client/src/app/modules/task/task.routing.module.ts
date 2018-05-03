import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TaskComponent } from './pages/task/task.component';
import { TaskAddComponent } from './pages/task-add/task-add.component';
import { TaskEditComponent } from './pages/task-edit/task-edit.component';

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
