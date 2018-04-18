import { Component, OnInit, AfterContentChecked } from '@angular/core';
import { RightsService } from '../../../services/rights.service';
import { ActivatedRoute } from '@angular/router';
import { Rights } from '../../../entity/rights';

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
    private rightsService: RightsService
  ) { }

  ngOnInit() {
    this.getRights();
  }

  getRights() {
    this.id = +this.route.snapshot.paramMap.get('id');
    this.rights = this.rightsService.getRights(this.id);
  }

  ngAfterContentChecked() {
    this.getRights();
  }

}
