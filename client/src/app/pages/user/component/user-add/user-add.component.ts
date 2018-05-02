import { Component, OnInit } from '@angular/core';
import { UserService } from '../../shared/user.service';
import { Rights } from '../../shared/user.model';

@Component({
  selector: 'app-user-add',
  templateUrl: './user-add.component.html',
  styleUrls: ['./user-add.component.css']
})
export class UserAddComponent implements OnInit {
  rights: Rights[];

  constructor(
    private userService: UserService
  ) { }

  ngOnInit() {
    this.userService.getListRights()
      .subscribe(
        res => this.rights = res,
        err => console.log(err)
      );
  }

}
