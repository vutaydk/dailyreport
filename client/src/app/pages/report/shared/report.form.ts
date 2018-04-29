import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';

export namespace ReportForm {
    export function newTaskForm(): FormGroup {
        return new FormBuilder().group({
            taskCode: ['', [Validators.required, Validators.pattern(/^[0-9]+$/)]],
            timeWork: ['', [Validators.required, Validators.pattern(/^[0-9]*$/)]],
            note: ['', [Validators.required]]
        });
    }

    export function newReportForm(): FormGroup {
        return new FormBuilder().group({
            projectCode: ['', [Validators.required, Validators.pattern(/^[0-9]+$/)]],
            employeeCode: ['', [Validators.required, Validators.pattern(/^[a-zA-Z]{4}$/)]],
            tasks: new FormBuilder().array([this.newTaskForm()])
        });
    }
}
