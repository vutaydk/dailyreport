import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { RightsService } from '../rights.service';

@Component({
  selector: 'app-rights-add',
  templateUrl: './rights-add.component.html',
  styleUrls: ['./rights-add.component.css'],
  providers: [RightsService]
})
export class RightsAddComponent implements OnInit {
  rightsForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private rightsService: RightsService
  ) { }

  ngOnInit() {
    this.rightsForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(6)]],
      level: ['', [Validators.required, Validators.pattern(/^[0-9]*$/)]]
    });
    console.log(this.rightsForm);
  }

  onAddRights() {
    if (this.rightsForm.valid) {
      this.rightsService.addRights(this.rightsForm.value)
        .then(res => console.log(res))
        .catch(err => console.log(err));
    }
  }

}
