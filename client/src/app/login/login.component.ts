import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Login } from '../entity/login';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  providers: [AuthService],
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  login: Login = new Login();

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  onSubmit() {
    this.authService.doLogin(this.login);
    this.router.navigate(['/rights']);
    return false;
  }

}
