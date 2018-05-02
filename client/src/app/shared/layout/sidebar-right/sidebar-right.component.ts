import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-sidebar-right',
  templateUrl: './sidebar-right.component.html',
  styleUrls: ['./sidebar-right.component.css']
})
export class SidebarRightComponent implements OnInit {

  @Output() searchItem: EventEmitter<string> = new EventEmitter();
  @Input() obj;

  ngOnInit(): void {

  }

  onSearch(search: string): void {
    this.searchItem.emit(search);
  }

}
