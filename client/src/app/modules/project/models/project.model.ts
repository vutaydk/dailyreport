export class Project {
    constructor(
        public id: number,
        public projectCode: string,
        public name: string,
        public startAt: string,
        public finishAt: string
    ) { }
}

export class ProjectDTO {
    constructor(
        public projectCode: string,
        public name: string,
        public startAt: string,
        public finishAt: string
    ) { }
}

export class ProjectDTOEdit {
    constructor(
        public name: string,
        public startAt: string,
        public finishAt: string
    ) { }
}
