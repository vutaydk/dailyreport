import { NgModule } from '@angular/core';
import { ChartsModule } from 'ng2-charts';
import { SharedModule } from '../../shared/shared.module';
import { ProjectRoutingModule } from './project.routing.module';
import { ProjectComponent } from './pages/project/project.component';
import { ProjectAddComponent } from './pages/project-add/project-add.component';
import { ProjectEditComponent } from './pages/project-edit/project-edit.component';
import { ProjectManagementComponent } from './pages/project-management/project-management.component';
import { ProjectChartComponent } from './pages/project-management/project-chart.component';
import { ProjectService } from './project.service';

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
  ],
  providers: [ProjectService]
})
export class ProjectModule { }
