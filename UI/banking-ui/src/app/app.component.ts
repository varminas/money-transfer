import { Component } from '@angular/core';
import { AuthService } from './shared/auth.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent {
    constructor(private authService: AuthService, private http: HttpClient, private router: Router) {
        // this.authService.authenticate(undefined, undefined);
    }

    logout() {
        // TODO check with finally from rxjs this.http.post('logout', {}).finally(() => {
        this.http.post('logout', {}).subscribe(() => {
            this.authService.authenticated = false;
            this.router.navigateByUrl('/login');
        });
    }
}
