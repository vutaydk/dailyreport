import { Component, Input, OnChanges, ViewChild } from '@angular/core';
import { BaseChartDirective } from 'ng2-charts/ng2-charts';
import { TaskForm } from '../../../task/task.form';
import { ProjectChart, Task } from '../../models/projectchart.model';

@Component({
  selector: 'app-project-chart',
  templateUrl: './project-chart.component.html',
  styleUrls: ['./project-chart.component.css']
})
export class ProjectChartComponent implements OnChanges {

  @Input() projectSelected;
  @Input() taskSelected;
  @Input() projectJson: ProjectChart[] = [];
  @ViewChild('pChart') baseChart: BaseChartDirective;
  projects: ProjectChart[] = [];
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

  ngOnChanges() {
    this.projects = [];
    let filtered: ProjectChart[];
    if (this.projectSelected.match(/^[0-9]*$/) && this.taskSelected.match(/^[0-9]*$/)) {
      filtered = this.projectJson.filter(p => p.id === +this.projectSelected).map(p1 => {
        return {
          ...p1,
          tasks: p1.tasks.filter(t => t.taskId === +this.taskSelected)
        };
      });
    } else if (this.projectSelected.match(/^[0-9]*$/)) {
      filtered = this.projectJson.filter(p => p.id === +this.projectSelected);
    } else if (this.taskSelected.match(/^[0-9]*$/)) {
      filtered = this.projectJson.filter(p => p).map(p1 => {
        return {
          ...p1,
          tasks: p1.tasks.filter(t => t.taskId === +this.taskSelected)
        };
      });
    } else {
      filtered = [...this.projectJson];
    }
    this.projects = [...filtered];
    filtered = [];
    this.getChartData();
  }

  getChartData(): void {
    this.resetData();
    if (!this.projects.length) {
      this.hasChartData = false;
      return;
    }
    const chart: Map<number, Task> = new Map<number, Task>();
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
      this.chartData.push(c.timeWork);
      this.chartLabels.push(c.taskName);
      this.tableData.tasks.push(c);
      this.tableData.total += c.timeWork;
    });
    this.hasChartData = true;
    if (this.baseChart) {
      this.baseChart.chart.update();
    }
  }

  resetData() {
    this.chartData.splice(0, this.chartData.length);
    this.chartLabels.splice(0, this.chartLabels.length);
    this.tableData.total = 0;
    this.tableData.tasks.splice(0, this.tableData.tasks.length);
  }

}
