import { Component, OnInit } from '@angular/core';
import { NgbDateParserFormatter } from '@ng-bootstrap/ng-bootstrap/datepicker/ngb-date-parser-formatter';
import { ReportService } from '../../shared/report.service';
import { Report } from '../../shared/report.model';

@Component({
  selector: 'app-report-managerment',
  templateUrl: './report-managerment.component.html',
  styleUrls: ['./report-managerment.component.css']
})
export class ReportManagermentComponent implements OnInit {

  reports: Report[];
  employeeSearchResults: string[] = [];
  isResultDisplay: boolean;

  constructor(
    private reportService: ReportService
  ) { }

  ngOnInit(): void {
    this.reportService.getList().subscribe(
      res => this.reports = res,
      err => console.log(err.message)
    );
  }

  onSearchEmp() {
    this.employeeSearchResults = [];
    //this.reports.filter(r => this.employeeSearchResults.push(r.employeeCode));
  }

  onToggleResult(): void {
    this.isResultDisplay = !this.isResultDisplay;
    this.onSearchEmp();
  }

  onSelectedEmp(event) {
    console.log(event);
    console.log(event.target.text.trim());
  }
  /*
  onlick, onkeyup: display result (toggle)
  lọc mảng lấy employeeCode, filter code trùng nhau,
  onclick item lấy employeeCode => filter reports lấy kết quả, hiển thị ra bảng

  */
}
