import { Component, OnInit } from '@angular/core';
import { Rights } from '../../entity/rights';
import { RightsService } from './rights.service';

@Component({
  selector: 'app-rights',
  templateUrl: './rights.component.html',
  styleUrls: ['./rights.component.css'],
  providers: [RightsService]
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
