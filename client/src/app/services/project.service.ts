import { Injectable } from '@angular/core';
import { PROJECT } from './project-mock';


@Injectable()
export class ProjectService {

    constructor() { }

    getProjects() {
        return PROJECT;
    }

    getProject(id: number) {
        return this.getProjects().find(p => p.id === id);
    }
}
