import { NavComponent, SidebarComponent, SidebarRightComponent } from '../../shared/layout';
import { ProjectComponent } from './project.component';
import { ProjectRoutingModule } from './project.routing.module';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ProjectRoutingModule,
    NgbModule.forRoot()
  ],
  declarations: [
    ProjectComponent,
    NavComponent,
    SidebarComponent,
    SidebarRightComponent
  ]
})
export class ProjectModule { }
