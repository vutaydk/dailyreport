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

  onToggleResult(): void {
    this.isResultDisplay = !this.isResultDisplay;
    this.onSearchEmp();
  }

  onSearchEmp() {
    this.employeeSearchResults.splice(0, this.employeeSearchResults.length);
    this.employeeSearchResults = [...this.employees];
  }

  onSelectedEmp(selected) {
    this.selectedEmp = selected;
    this.reportsFiltered.splice(0, this.reportsFiltered.length);
    this.reportsFiltered = this.reports.filter(
      r => r.employeeName.trim().toLowerCase().includes(selected.trim().toLowerCase())
    );
  }

  /* get list employee with employee code unduplicated */
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
