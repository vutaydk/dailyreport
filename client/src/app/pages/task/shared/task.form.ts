import { FormGroup, FormControl, Validators } from '@angular/forms';

export namespace TaskForm {
    export function newTaskForm(): FormGroup {
        return new FormGroup({
            taskCode: new FormControl('', [Validators.required, Validators.pattern(/^[a-zA-Z]{4}$/)]),
            name: new FormControl('', [Validators.required, Validators.minLength(4)])
        });
    }
}
