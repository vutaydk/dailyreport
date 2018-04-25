import { NgModule } from '@angular/core';

import { SharedModule } from '../../shared/shared.module';
import { ReportRoutingModule } from './report.routing.module';

import { ReportComponent } from './report.component';
import { ReportAddComponent } from './report-add/report-add.component';
import { ReportEditComponent } from './report-edit/report-edit.component';
import { ReportManagermentComponent } from './report-managerment/report-managerment.component';
import { ReportService } from './report.service';

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
