import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-root',
  template: `
    <router-outlet>
      <a routerLink="login">Login</a> -
      <a routerLink="rights">Rights</a>
    </router-outlet>
  `,
})
export class AppComponent {
  title = 'app';
}
