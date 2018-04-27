import { Component, OnInit } from '@angular/core';
import { ReportService } from '../service/report.service';
import { Report, Employee } from '../../../interfaces/report.interface';
import { NgbDateParserFormatter } from '@ng-bootstrap/ng-bootstrap/datepicker/ngb-date-parser-formatter';

@Component({
  selector: 'app-report-managerment',
  templateUrl: './report-managerment.component.html',
  styleUrls: ['./report-managerment.component.css']
})
export class ReportManagermentComponent implements OnInit {
  reports: Report[] = [];
  reportsFiltered: Report[] = [];
  employees: Employee[] = [];
  employeeSearchResults: Employee[] = [];
  selectedEmp = '';
  isResultDisplay: boolean;

  constructor(
    private reportService: ReportService
  ) { }


  /*
    get list report from service
    assign list to reports
    assign reports to reportsFiltered
  */
  ngOnInit() {
    this.reportService.getReports().subscribe(
      res => {
        this.reports = res;
        this.reportsFiltered = [...this.reports];
        this.getListEmp();
      },
      err => console.log(err)
    );
  }

  /*
    toggle employee result search
  */
  onToggleResult(): void {
    this.employeeSearchResults.splice(0, this.employeeSearchResults.length);
    this.employeeSearchResults = [...this.employees];
    this.isResultDisplay = !this.isResultDisplay;
  }

  /*
    filter reports by employeeName = s
    filter employees by employeeName s
  */
  onSearchEmp(filter) {
    const s = filter.replace(/ /g, '').toLowerCase();
    this.reportsFiltered.splice(0, this.reportsFiltered.length);
    this.employeeSearchResults.splice(0, this.employeeSearchResults.length);

    this.reportsFiltered = this.reports.filter(
      r => this.isContain(r.employeeName, s) || this.isContain(r.employeeCode, s)
    );
    this.employeeSearchResults = this.employees.filter(
      e => this.isContain(e.employeeName, s) || this.isContain(e.employeeCode, s)
    );
  }

  onSelectedEmp(s) {
    this.selectedEmp = s;
    this.onSearchEmp(s);
  }

  isContain(i: string, c: string): boolean {
    return i.replace(/ /g, '').toLowerCase().includes(c);
  }

  /*
    get list employee from list reports with employee code unduplicated
    assign list to employees
  */
  getListEmp() {
    this.employees.splice(0, this.employees.length);
    const emps: Map<string, Employee> = new Map<string, Employee>();
    for (const e of this.reports) {
      const emp: Employee = { employeeCode: e.employeeCode, employeeName: e.employeeName };
      if (!emps.has(e.employeeCode)) {
        emps.set(e.employeeCode, emp);
      }
    }
    emps.forEach(e => {
      this.employees.push(e);
    });
  }
}
