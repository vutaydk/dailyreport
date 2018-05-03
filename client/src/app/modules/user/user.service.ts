import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppConfig } from '../../config/app.config';
import { Observable } from 'rxjs/Observable';
import { Rights, User, UserDTO } from './models/user.model';

@Injectable()
export class UserService {

    private readonly rightsUrl: string;
    private readonly userUrl: string;

    constructor(
        private http: HttpClient
    ) {
        this.rightsUrl = `${AppConfig.API_URL}/rights`;
        this.userUrl = `${AppConfig.API_URL}/user`;
    }

    getList(): Observable<User[]> {
        const url = `${this.userUrl}/get-all`;
        return this.get<User[]>(url);
    }

    getListRights(): Observable<Rights[]> {
        const url = `${this.rightsUrl}/get-all`;
        return this.get<Rights[]>(url);
    }

    create(user: UserDTO): Observable<User> {
        const url = `${this.userUrl}`;
        return this.post<User>(url, user);
    }

    update(user: UserDTO, id: number): Observable<User> {
        const url = `${this.userUrl}/${id}`;
        return this.post<User>(url, user);
    }

    private get<T>(url: string) {
        const headers = new HttpHeaders({ 'Authorization': `Bearer ${localStorage.getItem('token')}` });
        return this.http.get<T>(url, { headers: headers });
    }

    private post<T>(url: string, data: any) {
        const headers = new HttpHeaders({ 'Authorization': `Bearer ${localStorage.getItem('token')}` });
        headers.append('Content-Type', 'application/json');
        return this.http.post<T>(url, data, { headers: headers });
    }

}
