export class Task {
    constructor(
        public id: number,
        public taskCode: string,
        public name: string
    ) { }
}

export class TaskDTO {
    constructor(
        public taskCode: string,
        public name: string
    ) { }
}
