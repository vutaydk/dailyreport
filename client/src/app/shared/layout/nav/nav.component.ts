import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  constructor(
    private auth: AuthService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  onLogout(): void {
    this.auth.logout()
      .then((res) => {
        console.log(res);
        localStorage.removeItem('token');
        this.router.navigateByUrl('/login')
      })
      .catch((res) => {
        console.log(res);
      });
  }

}
