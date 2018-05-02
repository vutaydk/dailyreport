export class UserDTO {
    constructor(
        public employeeCode: string,
        public password: string,
        public email: string,
        public name: string,
        public rights: Rights
    ) { }
}

export class User {
    constructor(
        public id: number,
        public employeeCode: string,
        public password: string,
        public email: string,
        public name: string,
        public rights: Rights
    ) { }
}

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
