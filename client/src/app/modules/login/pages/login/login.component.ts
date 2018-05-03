import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup } from '@angular/forms';
import { LoginService } from '../../login.service';
import { LoginForm } from '../../login.form';
import { Login } from '../../models/login.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor(
    private service: LoginService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loginForm = LoginForm.newLoginForm();
  }

  onSubmit(): void {
    const login: Login = {
      employeeCode: this.loginForm.get('employeeCode').value,
      password: this.loginForm.get('password').value
    };
    this.service.login(login).subscribe(
      res => {
        if (res.token && res.userId) {
          console.log(res.userId);
          console.log(res.token);
          localStorage.setItem('isLogged', res.userId);
          localStorage.setItem('token', res.token);
          this.router.navigateByUrl('/project');
        }
      },
      err => console.log(err)
    );
  }

}
