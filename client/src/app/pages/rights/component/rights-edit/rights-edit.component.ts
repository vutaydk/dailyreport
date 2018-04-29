import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { RightsService } from '../../shared/rights.service';
import { RightsForm } from '../../shared/rights.form';
import { Rights } from '../../shared/rights.model';

@Component({
  selector: 'app-rights-edit',
  templateUrl: './rights-edit.component.html',
  styleUrls: ['./rights-edit.component.css']
})
export class RightsEditComponent implements OnInit {

  rightsForm: FormGroup;
  rights: Rights;
  id: number;

  constructor(
    private route: ActivatedRoute,
    private rightsService: RightsService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.rightsForm = RightsForm.newRightsForm();
    this.route.url.subscribe(
      url => this.onRouterChange(url)
    );
  }

  onSubmit(): void {
    if (this.rightsForm.valid) {
      // update rights
      console.log(this.rightsForm.value);
    }
  }

  onRouterChange(url): void {
    const id = Number(url[0].path);
    this.rightsService.findById(id).subscribe(
      res => this.rights = res,
      err => { console.log(err); this.router.navigate(['404page']); }
    );
  }

}
