export class Report {
    constructor(
        public projectId: number,
        public employeeCode: string,
        public employeeName: string,
        public startAt: string,
        public finishAt: string,
        public tasks: PTask[]
    ) { }
}

export class PTask {
    constructor(
        public taskId: number,
        public taskName: string,
        public timeWork: number
    ) { }
}

export class ReportDTO {
    constructor(
        public projectId: number,
        public tasks: PTaskDTO[]
    ) { }
}

export class PTaskDTO {
    constructor(
        public taskId: number,
        public timeWork: number,
        public note: string
    ) { }
}

export class Project {
    constructor(
        public id: number,
        public name: string
    ) { }
}

export class Task {
    constructor(
        public id: number,
        public name: string
    ) { }
}
