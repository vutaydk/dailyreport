import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { ProjectDTO, ProjectDTOEdit } from './models/project.model';
import * as _ from 'lodash';

export namespace ProjectForm {
    export function newProjectForm(project: ProjectDTO = new ProjectDTO('', '', '', '')): FormGroup {
        return new FormBuilder().group({
            projectCode: [project.projectCode, [Validators.required, Validators.pattern(/^[a-zA-Z0-9]{4}$/)]],
            name: [project.name, [Validators.required, Validators.minLength(6)]],
            startAt: [convertDate(project.startAt), [Validators.required]],
            finishAt: [convertDate(project.finishAt), [Validators.required]]
        });
    }
    export function newProjectFormEdit(project: ProjectDTOEdit = new ProjectDTOEdit('', '', '')): FormGroup {
        return new FormBuilder().group({
            name: [project.name, [Validators.required, Validators.minLength(6)]],
            startAt: [convertDate(project.startAt), [Validators.required]],
            finishAt: [convertDate(project.finishAt), [Validators.required]]
        });
    }
}

function convertDate(date) {
    return _.fromPairs([
        ['year', Number(date.split('-')[0])],
        ['month', Number(date.split('-')[1])],
        ['day', Number(date.split('-')[2])]
    ]);
}
