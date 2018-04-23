import { Component, OnInit, AfterContentChecked } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Rights } from '../../../entity/rights';
import { RightsService } from '../rights.service';

@Component({
  selector: 'app-rights-edit',
  templateUrl: './rights-edit.component.html',
  providers: [RightsService],
  styleUrls: ['./rights-edit.component.css']
})
export class RightsEditComponent implements OnInit, AfterContentChecked {

  rights: Rights;
  id: number;

  constructor(
    private route: ActivatedRoute,
    private rightsService: RightsService,
    private router: Router
  ) { }

  ngOnInit() {
    this.getRights();
  }

  getRights() {
    this.id = +this.route.snapshot.paramMap.get('id');
    this.rights = this.rightsService.getRights(this.id);
    if (!this.rights) {
      this.router.navigate(['404page']);
    }
  }

  ngAfterContentChecked() {
    this.getRights();
  }

}
