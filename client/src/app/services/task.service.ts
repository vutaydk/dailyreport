import { Injectable } from '@angular/core';
import { TASK } from './task-mock';

@Injectable()
export class TaskService {

  constructor() { }

  getTasks() {
    return TASK;
  }

  getTask(id: number) {
    return this.getTasks().find(t => t.id === id);
  }
}
