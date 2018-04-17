import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-sidebar-right',
  templateUrl: './sidebar-right.component.html',
  styleUrls: ['./sidebar-right.component.css']
})
export class SidebarRightComponent implements OnInit {

  @Input() obj: any;

  constructor() { }

  ngOnInit() {
    console.log(this.obj);
  }

}
