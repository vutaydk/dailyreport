import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup } from '@angular/forms';
import { LoginService } from '../shared/login.service';
import { LoginForm } from '../shared/login.form';

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

  ngOnInit() {
    this.loginForm = LoginForm.newLoginForm();
  }

  onSubmit(): void {

    const employeeCode = this.loginForm.get('employeeCode').value;
    const password = this.loginForm.get('password').value;

    this.service.login(employeeCode, password).subscribe(
      res => {
        localStorage.setItem('token', res.token);
        console.log(res.token);
        this.router.navigateByUrl('/project');
      },
      err => console.log(err)
    );
  }

}
