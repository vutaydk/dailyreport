import { ReportComponent } from './';
import { ReportRoutingModule } from './report.routing.module';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';


@NgModule({
  imports: [
    FormsModule,
    ReportRoutingModule
  ],
  declarations: [
    ReportComponent
  ]
})
export class ReportModule { }
