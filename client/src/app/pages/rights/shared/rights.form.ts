import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { RightsDTO } from './rights.model';

export namespace RightsForm {
    export function newRightsForm(rights: RightsDTO = new RightsDTO('', 1)): FormGroup {
        return new FormBuilder().group({
            name: [rights.name, [Validators.required, Validators.minLength(6)]],
            level: [rights.level, [Validators.required, Validators.pattern(/^[0-9]*$/)]]
        });
    }
}
