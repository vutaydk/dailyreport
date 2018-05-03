import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Subject } from 'rxjs/Subject';
import { debounceTime } from 'rxjs/operator/debounceTime';
import { RightsService } from '../../shared/rights.service';
import { RightsForm } from '../../shared/rights.form';
import { RightsDTO } from '../../shared/rights.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-rights-add',
  templateUrl: './rights-add.component.html',
  styleUrls: ['./rights-add.component.css']
})
export class RightsAddComponent implements OnInit {
  private _message = new Subject<string>();
  rightsForm: FormGroup;
  isSubmitting: boolean;
  message: string;
  type: string;

  constructor(
    private rightsService: RightsService
  ) { }

  ngOnInit(): void {
    this.rightsForm = RightsForm.newRightsForm();
    this._message.subscribe((message) => this.message = message);
    debounceTime.call(this._message, 4000).subscribe(() => this.message = null);
  }

  onSubmit(): void {
    if (this.rightsForm.valid) {
      if (this.isSubmitting) {
        return;
      }
      this.isSubmitting = true;
      // data
      const rights: RightsDTO = {
        name: this.rightsForm.get('name').value,
        level: this.rightsForm.get('level').value
      };
      // create rights
      this.rightsService.create(rights).subscribe(
        res => {
          console.log(res);
          this.rightsForm.reset();
          this.type = 'success';
          this._message.next(`Add successfully!`);
          this.isSubmitting = false;
        },
        err => {
          console.log(err.message);
          this.type = 'danger';
          this._message.next(`Add fail! Please try again.`);
          this.isSubmitting = false;
        }
      );
    }
  }

}
