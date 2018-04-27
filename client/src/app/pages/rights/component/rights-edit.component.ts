import { Component, OnInit } from '@angular/core';
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
export class RightsEditComponent implements OnInit {
  rightsForm: FormGroup;
  rights: Rights;
  isSubmitting: boolean;

  constructor(
    private route: ActivatedRoute,
    private rightsService: RightsService,
    private router: Router
  ) { }

  ngOnInit() {
    this.rightsForm = RightsInterface.newRightsForm();
    this.route.url.subscribe(
      url => this.onRouterChange(url)
    );
  }

  onUpdateRights(): void {
    if (this.rightsForm.valid) {
      if (this.isSubmitting) {
        return;
      }
      this.isSubmitting = true;
      this.rightsService.updateRights(this.rightsForm.value)
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

  onRouterChange(url) {
    const pathParam = Number(url[0].path);
    if (!pathParam) {
      // param is not a number, redirect to 404
      this.router.navigate(['404page']);
      return;
    }
    this.rightsService.getRights(pathParam).subscribe(
      res => this.rights = res,
      err => this.router.navigate(['404page'])
    );
  }

  get name() {
    return this.rightsForm.get('name');
  }

  get level() {
    return this.rightsForm.get('level');
  }
}
