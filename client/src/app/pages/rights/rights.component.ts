import { Component, OnInit } from '@angular/core';
import { RightsService } from '../../services/rights.service';
import { Rights } from '../../entity/rights';

@Component({
  selector: 'app-rights',
  templateUrl: './rights.component.html',
  providers: [RightsService],
  styleUrls: ['./rights.component.css']
})
export class RightsComponent implements OnInit {

  rights: Rights[];

  constructor(
    private rightsService: RightsService
  ) { }

  ngOnInit() {
    this.rights = this.rightsService.getAllRights();
  }

}
