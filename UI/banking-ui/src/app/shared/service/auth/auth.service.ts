import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Credentials } from './credentials';

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private readonly BASE_URL = '/banking/';

    private _authenticated = false;
    private _jwtToken: string;
    private _userId: string;

    constructor(private http: HttpClient) {
    }

    authenticate(credentials: Credentials, callback) {

        const headers = new HttpHeaders(credentials ? {
            authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)
        } : {});

        const URL = this.BASE_URL + 'tokens';
        this.http.get(URL, { headers: headers }).subscribe(response => {
            if (response['jwt']) {
                this._authenticated = true;
                this._jwtToken = response['jwt'];
                this._userId = response['userId'];
            } else {
                this._authenticated = false;
                this._jwtToken = undefined;
                this._userId = undefined;
            }
            return callback && callback();
        });
    }

    get authenticated(): boolean {
        return this._authenticated;
    }

    get userId(): string {
        return this._userId;
    }

    get token(): string {
        return this._jwtToken;
    }

    logout(): void {
        this._authenticated = false;
        this._jwtToken = undefined;
    }
}
