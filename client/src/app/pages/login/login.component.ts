import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { Login } from '../../entity/login';

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

  onSubmit(loginForm) {
    console.log(loginForm);
    // this.authService.doLogin(loginForm.value);
    // this.router.navigate(['/rights']);
  }

}
