import { UserDTO, Rights, RightsDTO } from './models/user.model';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

export namespace UserForm {
    export function newUserForm(user: UserDTO = new UserDTO('', '', '', '', 1)): FormGroup {
        return new FormBuilder().group({
            employeeCode: [user.employeeCode, [Validators.required, Validators.pattern(/^[a-zA-Z0-9]{4}$/)]],
            password: [user.password, [Validators.required]],
            email: [user.email, [Validators.required]],
            name: [user.name, [Validators.required]],
            rights: [user.rights, [Validators.required]]
        });
    }
}
