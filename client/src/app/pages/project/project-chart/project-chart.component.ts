import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';
import { ProjectService } from '../project.service';
import { Project, ProjectJson } from '../../../entity/project';
import { Task, TaskJson } from '../../../entity/task';

@Component({
  selector: 'app-project-chart',
  templateUrl: './project-chart.component.html',
  styleUrls: ['./project-chart.component.css'],
  providers: [ProjectService]
})
export class ProjectChartComponent implements OnInit, OnChanges {
  @Input() projectSelected: number;
  @Input() taskSelected: number;
  projectJson: ProjectJson[] = [];
  chartOptions = {
    responsive: true
  };
  chartData: number[] = [];
  chartLabels: string[] = [];
  tableData = {
    tasks: [],
    total: 0
  };

  constructor(
    private _projectService: ProjectService
  ) { }

  ngOnInit() {
    this.projectJson = this._projectService.getProjectsJson();
    this.getData();
  }

  /*
    1. get all if projectSelected and taskSelected undefined
    2. get all task by projectId if projectSelected != undefinded and taskSelected undefined
    3. get one task of all project if projectSelected undefined and taskSelected != undefined
    4. get one task of one project if projectSelected and taskSelected != undefined
  */
  ngOnChanges(changes: SimpleChanges) {
    if (this.projectSelected) {
      this.projectJson = this._projectService.getProjectsJson().filter(p => p.id === +this.projectSelected);
    }
    console.log(this.projectSelected, this.taskSelected);
    console.table(this.projectJson);
    this.getData();
  }

  resetChart() {
    this.chartData = [];
    this.chartLabels = [];
    this.tableData.tasks = [];
  }


  getData(): void {
    this.resetChart();
    const chart: Map<number, TaskJson> = new Map<number, TaskJson>();
    for (const p of this.projectJson) {
      for (const t of p.tasks) {
        if (chart.has(t.taskId)) {
          // if timework not undefined
          if (chart.get(t.taskId).timeWork) {
            t.timeWork += chart.get(t.taskId).timeWork;
          }
        }
        chart.set(t.taskId, t);
      }
    }
    chart.forEach(c => {
      this.chartLabels.push(c.taskName);
      this.chartData.push(c.timeWork);
      this.tableData.tasks.push(c);
      this.tableData.total += c.timeWork;
    });
  }

}
