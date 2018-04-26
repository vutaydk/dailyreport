import { Component, OnInit } from '@angular/core';
import { RightsService } from './rights.service';
import { Rights } from '../../interfaces/rights.interface';

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
    this.rightsService.getAllRights().subscribe(
      res => this.rights = res,
      err => console.log(err)
    );
  }

  onSearch(event: string) {
    const filter = event.toLowerCase().trim();
    this.rightsService.getAllRights().subscribe(
      res => this.rights = res.filter(r => r.name.toLowerCase().trim().includes(filter))
    );
  }
}
