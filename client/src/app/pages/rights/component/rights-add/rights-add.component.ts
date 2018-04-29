import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { RightsService } from '../../shared/rights.service';
import { RightsForm } from '../../shared/rights.form';

@Component({
  selector: 'app-rights-add',
  templateUrl: './rights-add.component.html',
  styleUrls: ['./rights-add.component.css']
})
export class RightsAddComponent implements OnInit {

  rightsForm: FormGroup;

  constructor(
    private rightsService: RightsService
  ) { }

  ngOnInit(): void {
    this.rightsForm = RightsForm.newRightsForm();
  }

  onSubmit(): void {
    if (this.rightsForm.valid) {
      // this.rightsService.addRights(this.rightsForm.value)
      //   .then(res => console.log(res))
      //   .catch(err => console.log(err));
    }
  }

}
