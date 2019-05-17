import { Component, Input, OnInit } from '@angular/core';
import { AuthService } from '../../shared/service/auth/auth.service';
import { User } from '../../shared/service/user/user';

@Component({
    selector: 'app-personal-details',
    templateUrl: './personal-details.component.html',
    styleUrls: ['./personal-details.component.scss']
})
export class PersonalDetailsComponent implements OnInit {

    @Input() user: User;

    constructor(public authService: AuthService) {
    }

    ngOnInit() {
    }

}
