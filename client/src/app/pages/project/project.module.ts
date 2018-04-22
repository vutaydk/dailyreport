import { NgModule } from '@angular/core';
import { ChartsModule } from 'ng2-charts';

import { SharedModule } from '../../shared/shared.module';
import { ProjectRoutingModule } from './project.routing.module';

import { ProjectComponent } from './project.component';
import { ProjectAddComponent } from './project-add/project-add.component';
import { ProjectEditComponent } from './project-edit/project-edit.component';
import { ProjectManagementComponent } from './project-management/project-management.component';
import { ProjectChartComponent } from './project-chart/project-chart.component';

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
