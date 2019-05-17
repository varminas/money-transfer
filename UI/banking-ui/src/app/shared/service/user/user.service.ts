import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './user';
import { map } from 'rxjs/operators';
import { AuthService } from '../auth/auth.service';

@Injectable({
    providedIn: 'root'
})
export class UserService {
    private readonly BASE_URL = '/banking/';

    constructor(private http: HttpClient, private auth: AuthService) {
    }

    getUser(): Observable<User> {
        const URL = this.BASE_URL + 'users/' + this.auth.userId;

        const jwt = this.auth.token;
        const headers = new HttpHeaders(jwt ? jwt : {});

        return this.http.get(URL, { headers: headers })
            .pipe(
                map((response: any) => {
                    return {
                        username: response.username,
                        firstName: response.firstName,
                        lastName: response.lastName,
                    };
                })
            );
    }
}
