import { Component, Input, OnChanges } from '@angular/core';
import { Project, ProjectJson } from '../../../entity/project';
import { Task, TaskJson } from '../../../entity/task';

@Component({
  selector: 'app-project-chart',
  templateUrl: './project-chart.component.html',
  styleUrls: ['./project-chart.component.css']
})
export class ProjectChartComponent implements OnChanges {
  @Input() projectSelected;
  @Input() taskSelected;
  @Input() projectJson: ProjectJson[] = [];
  projects: ProjectJson[] = [];
  hasChartData: boolean;
  chartOptions = {
    responsive: true
  };
  chartData: number[] = [];
  chartLabels: string[] = [];
  tableData = {
    tasks: [],
    total: 0
  };

  constructor() { }

  ngOnChanges() {
    this.projects = [];
    if (this.projectSelected.match(/^[0-9]*$/)) {
      this.projects = [...this.projectJson.filter(p => p.id === +this.projectSelected)];
    } else {
      this.projects = [...this.projectJson];
    }
    this.getChartData();
  }

  getChartData(): void {
    this.resetData();
    if (!this.projects.length) {
      this.hasChartData = false;
      return;
    }
    const chart: Map<number, TaskJson> = new Map<number, TaskJson>();
    for (const p of this.projects) {
      for (const t of p.tasks) {
        const task = { ...t };
        if (chart.has(task.taskId)) {
          task.timeWork += chart.get(task.taskId).timeWork;
        }
        chart.set(task.taskId, task);
      }
    }
    chart.forEach(c => {
      this.chartLabels.push(c.taskName);
      this.chartData.push(c.timeWork);
      this.tableData.tasks.push(c);
      this.tableData.total += c.timeWork;
    });
    this.hasChartData = true;
  }

  resetData() {
    this.chartData = [];
    this.chartLabels = [];
    this.tableData.tasks = [];
  }
}
