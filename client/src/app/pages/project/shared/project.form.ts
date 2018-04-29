import { FormGroup, FormControl, Validators } from '@angular/forms';

export namespace ProjectForm {
    export function newProjectForm(): FormGroup {
        return new FormGroup({
            projectCode: new FormControl('', [Validators.required, Validators.pattern(/^[a-zA-Z]{4}$/)]),
            name: new FormControl('', [Validators.required, Validators.minLength(6)]),
            startAt: new FormControl('', [Validators.required]),
            finishAt: new FormControl('', [Validators.required])
        });
    }
}
