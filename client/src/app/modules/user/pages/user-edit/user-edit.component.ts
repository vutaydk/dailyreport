import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup } from '@angular/forms';
import { Subject } from 'rxjs/Subject';
import { debounceTime } from 'rxjs/operator/debounceTime';
import { UserService } from '../../shared/user.service';
import { UserForm } from '../../shared/user.form';
import { UserDTO } from '../../shared/user.model';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {

  private _message = new Subject<string>();
  userForm: FormGroup;
  isSubmitting: boolean;
  id: number;
  message: string;
  type: string;

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.route.url.subscribe(
      url => this.onRouterChange(url)
    );
    this.userForm = UserForm.newUserForm();

    // feedback message
    this._message.subscribe((message) => this.message = message);
    debounceTime.call(this._message, 4000).subscribe(() => this.message = null);
  }

  onRouterChange(url): void {
    this.id = Number(url[0].path);
    this.userService.findById(this.id).subscribe(
      res => {
        this.userForm = UserForm.newUserForm(res);
        console.log(this.userForm);
        console.log(res);
      },
      err => {
        console.log(err.message);
        this.router.navigate(['404page']);
      }
    );
  }

  onEditUser(): void {

    if (this.userForm.valid) {
      if (this.isSubmitting) {
        return;
      }
      this.isSubmitting = true;
      // data
      const user: UserDTO = {
        employeeCode: this.userForm.get('employeeCode').value,
        password: this.userForm.get('password').value,
        email: this.userForm.get('email').value,
        name: this.userForm.get('name').value,
        rights: this.userForm.get('rights').value
      };

      // update user
      this.userService.update(user, this.id).subscribe(
        res => {
          console.log(res);
          this.userForm.reset();
          this.type = 'success';
          this._message.next(`Update successfully!`);
          this.isSubmitting = false;
        },
        err => {
          console.log(err.message);
          this.type = 'danger';
          this._message.next(`Update fail! Please try again.`);
          this.isSubmitting = false;
        }
      );
    }
  }

}
