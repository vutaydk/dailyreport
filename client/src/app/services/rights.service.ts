import { Injectable } from '@angular/core';
import { RIGHTS } from './rights-mock';

@Injectable()
export class RightsService {

  constructor() { }

  getAllRights() {
    return RIGHTS;
  }

  getRights(id: number) {
    return this.getAllRights().find(r => r.id === id);
  }
}
