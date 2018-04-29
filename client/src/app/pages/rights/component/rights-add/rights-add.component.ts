import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { RightsService } from '../../shared/rights.service';
import { RightsForm } from '../../shared/rights.form';
import { RightsDTO } from '../../shared/rights.model';

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
      // data
      const rights: RightsDTO = {
        name: this.rightsForm.get('name').value,
        level: this.rightsForm.get('level').value
      };
      // create rights
      this.rightsService.create(rights).subscribe(
        res => { console.log(res); this.rightsForm.reset(); },
        err => console.log(err.message)
      );
    }
  }

}
