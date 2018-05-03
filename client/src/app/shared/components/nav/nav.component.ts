import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent {

  constructor(
    private auth: AuthService,
    private router: Router
  ) { }

  onLogout(): void {
    this.auth.logout()
      .then(req => {
        console.log(req);
        localStorage.removeItem('token');
        localStorage.removeItem('isLogged');
        this.router.navigateByUrl('/login');
      })
      .catch(err => {
        console.log(err);
      });
  }

}
