import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { RightsService } from '../rights.service';
import { RightsInterface } from '../../../interfaces/rights.interface';

@Component({
  selector: 'app-rights-add',
  templateUrl: './rights-add.component.html',
  styleUrls: ['./rights-add.component.css'],
  providers: [RightsService]
})
export class RightsAddComponent implements OnInit {
  rightsForm: FormGroup;

  constructor(
    private rightsService: RightsService
  ) { }

  ngOnInit() {
    this.rightsForm = RightsInterface.newRightsForm();
  }

  onAddRights() {
    if (this.rightsForm.valid) {
      this.rightsService.addRights(this.rightsForm.value)
        .then(res => console.log(res))
        .catch(err => console.log(err));
    }
  }

}
