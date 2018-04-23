import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { Login } from '../../entity/login';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  user: Login = new Login();

  constructor(
    private auth: AuthService,
    private router: Router
  ) { }

  onLogin(): void {
    this.auth.login(this.user)
      .then((user) => {
        console.log(user);
        localStorage.setItem('token', user.token);
        this.router.navigateByUrl('/project');
      })
      .catch((err) => {
        console.log(err);
      });
  }

}
