export class Report {
    constructor(
        public projectId: number,
        public tasks: PTask[]
    ) { }
}

export class PTask {
    constructor(
        public taskId: number,
        public timeWork: number,
        public note: string
    ) { }
}
