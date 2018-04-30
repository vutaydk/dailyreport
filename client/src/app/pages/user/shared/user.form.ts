import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Task, TaskDTO } from './task.model';

export namespace TaskForm {
    export function newTaskForm(task: TaskDTO = new TaskDTO('', '')): FormGroup {
        return new FormBuilder().group({
            taskCode: [task.taskCode, [Validators.required, Validators.pattern(/^[a-zA-Z0-9]{4}$/)]],
            name: [task.name, [Validators.required, Validators.minLength(4)]]
        });
    }
}
