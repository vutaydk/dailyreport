import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs/Subject';
import { debounceTime } from 'rxjs/operator/debounceTime';
import { UserService } from '../../shared/user.service';
import { Rights, User, UserDTO } from '../../shared/user.model';
import { FormGroup } from '@angular/forms';
import { UserForm } from '../../shared/user.form';

@Component({
  selector: 'app-user-add',
  templateUrl: './user-add.component.html',
  styleUrls: ['./user-add.component.css']
})
export class UserAddComponent implements OnInit {

  private _message = new Subject<string>();
  userForm: FormGroup;
  rights: Rights[];
  isSubmitting: boolean;
  message: string;
  type: string;

  constructor(
    private userService: UserService
  ) { }

  ngOnInit() {
    this.userForm = UserForm.newUserForm();
    this.userService.getListRights()
      .subscribe(
        res => this.rights = res,
        err => console.log(err)
      );
    this._message.subscribe((message) => this.message = message);
    debounceTime.call(this._message, 4000).subscribe(() => this.message = null);
  }

  onSubmit() {
    if (this.userForm.valid) {
      if (this.isSubmitting) {
        return;
      }
      this.isSubmitting = true;
      console.log(this.userForm.value);
      // data
      const user: UserDTO = {
        employeeCode: this.userForm.get('employeeCode').value,
        password: this.userForm.get('password').value,
        email: this.userForm.get('email').value,
        name: this.userForm.get('name').value,
        rights: Number(this.userForm.get('rights').value)
      };
      this.userService.create(user).subscribe(
        res => {
          console.log(res);
          this.userForm.reset();
          this.type = 'success';
          this._message.next(`Add successfully!`);
          this.isSubmitting = false;
        },
        err => {
          console.log(err);
          this.type = 'danger';
          this._message.next(`Add fail! Please try again.`);
          this.isSubmitting = false;
        }
      );
    }
  }

}
