import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { RightsService } from '../../shared/rights.service';
import { RightsForm } from '../../shared/rights.form';
import { RightsDTO } from '../../shared/rights.model';

@Component({
  selector: 'app-rights-edit',
  templateUrl: './rights-edit.component.html',
  styleUrls: ['./rights-edit.component.css']
})
export class RightsEditComponent implements OnInit {
  rightsForm: FormGroup;
  id: number;

  constructor(
    private route: ActivatedRoute,
    private rightsService: RightsService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.route.url.subscribe(
      url => this.onRouterChange(url)
    );
    this.rightsForm = RightsForm.newRightsForm();
  }

  onRouterChange(url): void {
    const id = Number(url[0].path);
    this.rightsService.findById(id).subscribe(
      res => this.rightsForm = RightsForm.newRightsForm(res),
      err => { console.log(err); this.router.navigate(['404page']); }
    );
  }

  onSubmit(): void {
    if (this.rightsForm.valid) {
      // data
      const rights: RightsDTO = {
        name: this.rightsForm.get('name').value,
        level: this.rightsForm.get('level').value
      };
      // update rights
      this.rightsService.update(this.id, rights).subscribe(
        res => { console.log(res); this.rightsForm.reset(); },
        err => console.log(err.message)
      );
    }
  }
}
