import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs/Subject';
import { debounceTime } from 'rxjs/operator/debounceTime';
import { RightsService } from '../../shared/rights.service';
import { RightsForm } from '../../shared/rights.form';
import { RightsDTO } from '../../shared/rights.model';

@Component({
  selector: 'app-rights-edit',
  templateUrl: './rights-edit.component.html',
  styleUrls: ['./rights-edit.component.css']
})
export class RightsEditComponent implements OnInit {

  private _message = new Subject<string>();
  rightsForm: FormGroup;
  isSubmitting: boolean;
  id: number;
  message: string;
  type: string;

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
    this._message.subscribe((message) => this.message = message);
    debounceTime.call(this._message, 4000).subscribe(() => this.message = null);
  }

  onRouterChange(url): void {
    this.id = Number(url[0].path);
    this.rightsService.findById(this.id).subscribe(
      res => this.rightsForm = RightsForm.newRightsForm(res),
      err => { console.log(err); this.router.navigate(['404page']); }
    );
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
      // update rights
      this.rightsService.update(rights, this.id).subscribe(
        res => {
          console.log(res);
          this.rightsForm.reset();
          this.type = 'success';
          this._message.next(`Update successfully!`);
          this.isSubmitting = false;
        },
        err => {
          console.log(err.message);
          this.type = 'danger';
          this._message.next(`Update fail! Please try again.`);
          this.isSubmitting = false;
        }
      );
    }
  }
}
