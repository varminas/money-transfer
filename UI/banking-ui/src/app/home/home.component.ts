import { Component, OnDestroy, OnInit } from '@angular/core';
import { UserService } from '../shared/service/user/user.service';
import { User } from '../shared/service/user/user';
import { AuthService } from '../shared/service/auth/auth.service';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, OnDestroy {
    private destroyed = new Subject<void>();

    user: User;

    constructor(private authService: AuthService, private userService: UserService) {
    }

    ngOnInit(): void {
        this.userService.getUser()
            .pipe(takeUntil(this.destroyed))
            .subscribe(user => this.user);
    }

    ngOnDestroy(): void {
        this.destroyed.next();
        this.destroyed.complete();
    }
}
