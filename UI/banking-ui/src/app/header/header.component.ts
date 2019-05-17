import { Component } from '@angular/core';
import { AuthService } from '../shared/auth.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

    constructor(private authService: AuthService, private router: Router) {
    }

    logout() {
        this.authService.logout();
        this.router.navigateByUrl('/login');
    }

}
