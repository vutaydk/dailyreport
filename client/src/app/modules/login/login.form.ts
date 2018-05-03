import { FormGroup, FormBuilder, Validators } from '@angular/forms';

export namespace LoginForm {
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
