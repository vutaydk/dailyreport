import { FormGroup, FormBuilder, Validators } from '@angular/forms';

export interface Login {
    employeeCode: string;
    password: string;
}

export namespace LoginInterface {
    export function newLoginForm(): FormGroup {
        return new FormBuilder().group({
            employeeCode: ['', [Validators.required]],
            password: ['', [Validators.required]]
        });
    }

    export function newRegisterForm(): FormGroup {
        return new FormBuilder().group({

        });
    }
}
