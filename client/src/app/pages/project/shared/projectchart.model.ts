export class ProjectChart {
    constructor(
        public id: number,
        public name: string,
        public tasks: Task[]
    ) { }
}

export class Task {
    constructor(
        public taskId: number,
        public taskName: string,
        public timeWork: number
    ) { }
}
