import { log } from 'util';
import { Component, OnInit } from '@angular/core';
import { ReportService } from '../../report.service';
import { Report } from '../../models/report.model';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import * as _ from 'lodash';

import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-report-managerment',
  templateUrl: './report-managerment.component.html',
  styleUrls: ['./report-managerment.component.css']
})
export class ReportManagermentComponent implements OnInit {

  reports: Report[];
  filteredReports: Report[];
  filteredEmployee: string[] = [];
  isResultDisplay: boolean;

  closeResult: string;

  start: string;
  finish: string;
  emp: string;

  filters = {};

  constructor(
    private reportService: ReportService,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
    this.reportService.getList().subscribe(
      res => {
        this.reports = res;
        this.applyFilters();
        console.log(this.reports);
      },
      err => console.log(err.message)
    );

  }

  applyFilters() {
    this.filteredReports = _.filter(this.reports, _.conforms(this.filters));
  }

  // filter by employee search
  onSearchEmp(property: string, rule: string) {
    this.filters[property] = val => val.replace(/ /g, '').toLowerCase().includes(rule);
    this.applyFilters();
  }

  // filter by start at select
  onStartAtSelect(property: string, rule: any) {
    let date;
    this.filters[property] = val => {
      date = this.stringToDate(val);
      console.log(date);


      if (rule['year'] === date['year'] && rule['month'] === date['month'] && rule['day'] > date['day']) {
        return;
      }
      if (rule['year'] === date['year'] && rule['month'] > date['month']) {
        return;
      }
      if (rule['year'] > date['year']) {
        return;
      }
      console.log(val);

      return val;
    };
    this.applyFilters();
  }

  // filter by finish at select
  onFinishAtSelect(property: string, rule: any) {
    let date;
    this.filters[property] = val => {
      date = this.stringToDate(val);
      if (rule['year'] === date['year'] && rule['month'] === date['month'] && rule['day'] < date['day']) {
        return;
      }
      if (rule['year'] === date['year'] && rule['month'] < date['month']) {
        return;
      }
      if (rule['year'] < date['year']) {
        return;
      }
      return val;
    };
    this.applyFilters();
  }

  onToggleResult(): void {
    this.isResultDisplay = !this.isResultDisplay;
  }

  onSelectedEmp(event) {
    this.onSearchEmp('employeeCode', event);
  }

  stringToDate(d) {
    return _.fromPairs([
      ['year', Number(d.split('-')[0])],
      ['month', Number(d.split('-')[1])],
      ['day', Number(d.split('-')[2])]
    ]);
  }

  open(content) {
    this.modalService.open(content).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

}
