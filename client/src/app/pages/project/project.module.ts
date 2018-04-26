import { NgModule } from '@angular/core';
import { ChartsModule } from 'ng2-charts';

import { SharedModule } from '../../shared/shared.module';
import { ProjectRoutingModule } from './project.routing.module';

import { ProjectComponent } from './component/project.component';
import { ProjectAddComponent } from './component/project-add.component';
import { ProjectEditComponent } from './component/project-edit.component';
import { ProjectManagementComponent } from './component/project-management.component';
import { ProjectChartComponent } from './component/project-chart.component';

@NgModule({
  imports: [
    ChartsModule,
    SharedModule,
    ProjectRoutingModule
  ],
  declarations: [
    ProjectComponent,
    ProjectAddComponent,
    ProjectEditComponent,
    ProjectManagementComponent,
    ProjectChartComponent
  ]
})
export class ProjectModule { }
