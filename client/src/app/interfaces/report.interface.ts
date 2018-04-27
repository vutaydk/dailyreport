import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { TaskJSON } from './task.interface';

export interface Report {
    employeeCode: string;
    projectCode: string;
    tasks: TaskJSON[];
}

export namespace ReportInterface {
    export function newTaskForm(): FormGroup {
        return new FormBuilder().group({
            taskCode: ['', [Validators.required, Validators.pattern(/^[a-zA-Z]{4}$/)]],
            timeWork: ['', [Validators.required, Validators.pattern(/^[0-9]*$/)]],
            note: ['', [Validators.required]]
        });
    }

    export function newReportForm(): FormGroup {
        return new FormBuilder().group({
            projectCode: ['', [Validators.required, Validators.pattern(/^[a-zA-Z]{4}$/)]],
            employeeCode: ['', [Validators.required, Validators.pattern(/^[a-zA-Z]{4}$/)]],
            tasks: new FormBuilder().array([this.newTaskForm()])
        });
    }
}
