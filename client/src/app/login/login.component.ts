import { Login } from '../entity/login';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: [ './login.component.css' ]
})
export class LoginComponent implements OnInit {

  login: Login = new Login();

  constructor() { }

  ngOnInit() {
  }

  onSubmit() {
    alert('username:' + this.login.username);
    alert('password:' + this.login.password);
    return false;
  }
}
