import { TaskJson } from './task';

export class Project {
  id: number;
  projectCode: string;
  name: string;
  startAt: string;
  finishAt: string;
}

export class ProjectJson {
  id: number;
  name: string;
  tasks: TaskJson[];
}
