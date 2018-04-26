import { AppComponent } from './app.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AuthService } from './services/auth.service';
import { AppRoutingModule } from './app.routing.module';
import { Authenticated } from './services/authenticated';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
    Authenticated,
    AuthService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
