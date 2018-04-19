import { Page404Component } from './page404.component';
import { Page404RoutingModule } from './page404.routing.module';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';


@NgModule({
  imports: [
    FormsModule,
    Page404RoutingModule
  ],
  declarations: [
    Page404Component
  ]
})
export class Page404Module { }
