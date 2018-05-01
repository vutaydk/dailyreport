import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup } from '@angular/forms';
import { LoginService } from '../shared/login.service';
import { LoginForm } from '../shared/login.form';
import { Login } from '../shared/login.model';

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
        console.log(res);
        localStorage.setItem('isLogged', login.employeeCode);
        this.router.navigateByUrl('/project');
      },
      err => console.log(err)
    );
  }

}