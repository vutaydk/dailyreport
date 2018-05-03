import { NgModule } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { ReportRoutingModule } from './report.routing.module';
import { ReportComponent } from './pages/report/report.component';
import { ReportAddComponent } from './pages/report-add/report-add.component';
import { ReportEditComponent } from './pages/report-edit/report-edit.component';
import { ReportManagermentComponent } from './pages/report-mangement/report-managerment.component';
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
