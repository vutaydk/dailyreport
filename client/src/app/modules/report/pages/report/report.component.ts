import { Component } from '@angular/core';

@Component({
  selector: 'app-report',
  template: `
    <app-nav></app-nav>
    <app-sidebar></app-sidebar>
    <div class="col-sm-9 offset-sm-3 col-lg-10 offset-lg-2 pt-2 main">
      <router-outlet></router-outlet>
    </div>
    <!-- ./add-project form -->
  `
})
export class ReportComponent { }
