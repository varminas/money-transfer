import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private readonly BASE_URL = '/banking/';

    authenticated = false;
    jwtToken: string;

    constructor(private http: HttpClient) {
    }

    authenticate(credentials, callback) {

        const headers = new HttpHeaders(credentials ? {
            authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)
        } : {});

        const URL = this.BASE_URL + 'tokens';
        this.http.get(URL, { headers: headers }).subscribe(response => {
            if (response['jwt']) {
                this.authenticated = true;
                this.jwtToken = response['jwt'];
            } else {
                this.authenticated = false;
                this.jwtToken = undefined;
            }
            return callback && callback();
        });
    }
}
