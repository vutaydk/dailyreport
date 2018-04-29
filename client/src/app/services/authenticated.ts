import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth.service';
import { AppConfig } from '../config/app.config';

@Injectable()
export class Authenticated implements CanActivate {

    private loginUrl: string;

    constructor(
        private auth: AuthService,
        private http: HttpClient,
        private router: Router
    ) {
        this.loginUrl = AppConfig.API_URL + '/login';
    }

    canActivate(): boolean {
        const url = `${this.loginUrl}`;
        let isLogged: boolean = false;
        this.http.get<any>(url).subscribe(
            res => { console.log(res); isLogged = true; },
            err => this.router.navigateByUrl('/login')
        );
        return isLogged;
    }
}
