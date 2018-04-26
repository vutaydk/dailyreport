import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { Login } from '../../interfaces/login.interface';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  user: Login;

  constructor(
    private auth: AuthService,
    private router: Router
  ) { }

  onRegister(loginForm): void {
    console.log(loginForm);
    this.auth.register(this.user)
      .then((user) => {
        console.log(user);
        // localStorage.setItem('token', user.token);
      })
      .catch((err) => {
        console.log(err);
      });

    this.auth.logout();
  }

  onLogout(): void {
    this.auth.logout();
    console.log('logout sucess');
    this.router.navigateByUrl('/login');
  }

}
