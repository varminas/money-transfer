import { Component } from '@angular/core';
import { AuthService } from '../shared/auth.service';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent {

    greeting = {};

    constructor(private authService: AuthService) {
    }

    authenticated() {
        return this.authService.authenticated;
    }
}
