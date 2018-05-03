import { Component, OnInit } from '@angular/core';
import { RightsService } from '../../rights.service';
import { Rights } from '../../models/rights.model';

@Component({
  selector: 'app-rights',
  templateUrl: './rights.component.html',
  styleUrls: ['./rights.component.css']
})
export class RightsComponent implements OnInit {

  private list: Rights[];
  rights: Rights[];

  constructor(
    private rightsService: RightsService
  ) { }

  ngOnInit() {
    this.rightsService.getList().subscribe(
      res => this.list = this.rights = res,
      err => console.log(err)
    );
  }

  onSearch(event: string) {
    const filter = event.toLowerCase().trim();
    this.rights = this.list.filter(r => r.name.toLowerCase().trim().includes(filter));
  }

}
