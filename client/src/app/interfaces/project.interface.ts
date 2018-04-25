import { TaskJSON } from './task.interface';
import { FormGroup, FormControl, Validators } from '@angular/forms';


export interface Project {
    id: number;
    projectCode: string;
    name: string;
    startAt: string;
    finishAt: string;
}

export interface ProjectJSON {
    id: number;
    name: string;
    tasks: TaskJSON[];
}

export namespace ProjectInterface {
    export function newProjectForm(): FormGroup {
        return new FormGroup({
            projectCode: new FormControl('', [Validators.required, Validators.pattern(/^[a-zA-Z]{4}$/)]),
            name: new FormControl('', [Validators.required, Validators.minLength(6)]),
            startAt: new FormControl('', [Validators.required]),
            finishAt: new FormControl('', [Validators.required])
        });
    }
}
