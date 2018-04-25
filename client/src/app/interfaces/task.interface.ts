import { FormGroup, FormControl, Validators } from '@angular/forms';

export interface Task {
    id: number;
    taskCode: string;
    name: string;
}

export interface TaskJSON {
    taskId: number;
    taskName: string;
    timeWork: number;
}

export namespace TaskInterface {
    export function newTaskForm(): FormGroup {
        return new FormGroup({
            taskCode: new FormControl('', [Validators.required, Validators.pattern(/^[a-zA-Z]{4}$/)]),
            name: new FormControl('', [Validators.required, Validators.minLength(4)])
        });
    }
}
