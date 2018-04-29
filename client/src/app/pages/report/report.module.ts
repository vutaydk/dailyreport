import { NgModule } from '@angular/core';

import { SharedModule } from '../../shared/shared.module';
import { ReportRoutingModule } from './report.routing.module';

import { ReportComponent } from './conponent/report.component';
import { ReportAddComponent } from './conponent/report-add/report-add.component';
import { ReportEditComponent } from './conponent/report-edit/report-edit.component';
import { ReportManagermentComponent } from './conponent/report-mangement/report-managerment.component';
import { ReportService } from './shared/report.service';

@NgModule({
  imports: [
    SharedModule,
    ReportRoutingModule
  ],
  declarations: [
    ReportComponent,
    ReportAddComponent,
    ReportEditComponent,
    ReportManagermentComponent
  ],
  providers: [ReportService]
})
export class ReportModule { }
