import { FormGroup, FormControl, Validators } from '@angular/forms';

export interface Rights {
    id: number;
    name: string;
    level: number;
}

export namespace RightsInterface {
    export function newRightsForm(): FormGroup {
        return new FormGroup({
            name: new FormControl('', [Validators.required, Validators.minLength(6)]),
            level: new FormControl('', [Validators.required, Validators.pattern(/^[0-9]*$/)])
        });
    }
}
