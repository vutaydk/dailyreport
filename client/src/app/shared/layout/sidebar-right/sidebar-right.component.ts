import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-sidebar-right',
  templateUrl: './sidebar-right.component.html',
  styleUrls: ['./sidebar-right.component.css']
})
export class SidebarRightComponent implements OnInit {

  @Input() obj: any;
  result;

  constructor() { }

  ngOnInit() {
    this.result = this.obj;
  }

  onSearch(search): void {
    const filter = search.value.toLowerCase().trim();
    this.result = this.obj.filter(o => {
      return o.name.trim().toLowerCase().includes(filter);
    });
  }

}
