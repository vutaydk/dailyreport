import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { RightsService } from '../service/rights.service';
import { RightsInterface } from '../../../interfaces/rights.interface';

@Component({
  selector: 'app-rights-add',
  templateUrl: './rights-add.component.html',
  styleUrls: ['./rights-add.component.css'],
  providers: [RightsService]
})
export class RightsAddComponent implements OnInit {
  rightsForm: FormGroup;
  isSubmitting: boolean;

  constructor(
    private rightsService: RightsService
  ) { }

  ngOnInit() {
    this.rightsForm = RightsInterface.newRightsForm();
  }

  onAddRights() {
    if (this.rightsForm.valid) {
      if (this.isSubmitting) {
        return;
      }
      this.isSubmitting = true;
      this.rightsService.addRights(this.rightsForm.value)
        .then(res => {
          console.log(res);
          this.isSubmitting = false;
        })
        .catch(err => {
          console.log(err);
          this.isSubmitting = false;
        });
    }
  }

  get name() {
    return this.rightsForm.get('name');
  }

  get level() {
    return this.rightsForm.get('level');
  }
}
