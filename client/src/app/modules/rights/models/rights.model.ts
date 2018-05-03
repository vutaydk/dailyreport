export class Rights {
    constructor(
        public id: number,
        public name: string,
        public level: number
    ) { }
}

export class RightsDTO {
    constructor(
        public name: string,
        public level: number
    ) { }
}
