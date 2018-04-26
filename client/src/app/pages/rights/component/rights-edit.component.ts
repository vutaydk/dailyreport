import { Component, OnInit, AfterContentChecked } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RightsService } from '../service/rights.service';
import { Rights, RightsInterface } from '../../../interfaces/rights.interface';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-rights-edit',
  templateUrl: './rights-edit.component.html',
  providers: [RightsService],
  styleUrls: ['./rights-edit.component.css']
})
export class RightsEditComponent implements OnInit, AfterContentChecked {
  rightsForm: FormGroup;
  rights: Rights;
  id: number;

  constructor(
    private route: ActivatedRoute,
    private rightsService: RightsService,
    private router: Router
  ) { }

  ngOnInit() {
    this.rightsForm = RightsInterface.newRightsForm();
    this.getRights();
  }

  getRights() {
    this.id = +this.route.snapshot.paramMap.get('id');
    /* this.rights = this.rightsService.getRights(this.id);
    if (!this.rights) {
      this.router.navigate(['404page']);
    } */
  }

  ngAfterContentChecked() {
    this.getRights();
  }

  onUpdateRights(): void {
    if (this.rightsForm.valid) {
      // update rights
      console.log(this.rightsForm.value);
    }
  }

}
