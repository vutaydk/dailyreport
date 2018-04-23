import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import {
  NavComponent,
  SidebarRightComponent,
  SidebarComponent
} from './layout';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule,
    NgbModule.forRoot() // bootstrap
  ],
  declarations: [
    NavComponent,
    SidebarComponent,
    SidebarRightComponent
  ],
  exports: [
    NavComponent,
    SidebarComponent,
    SidebarRightComponent,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule,
    RouterModule,
  ]
})
export class SharedModule { }
