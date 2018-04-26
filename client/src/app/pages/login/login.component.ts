import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { Login, LoginInterface } from '../../interfaces/login.interface';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  constructor(
    private auth: AuthService,
    private router: Router
  ) { }

  ngOnInit() {
    this.loginForm = LoginInterface.newLoginForm();
  }

  onLogin(): void {
    if (!this.loginForm.valid) {
      return;
    }
    this.auth.login(this.loginForm.value)
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
