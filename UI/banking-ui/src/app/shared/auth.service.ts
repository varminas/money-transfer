import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private readonly BASE_URL = '/banking/';

    authenticated = false;

    constructor(private http: HttpClient) {
    }

    authenticate(credentials, callback) {

        const headers = new HttpHeaders(credentials ? {
            authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)
        } : {});

        const URL = this.BASE_URL + 'auth/tokens';
        this.http.get(URL, { headers: headers }).subscribe(response => {
            // TODO check correct response
            if (response['name']) {
                this.authenticated = true;
            } else {
                this.authenticated = false;
            }
            return callback && callback();
        });

    }
}
