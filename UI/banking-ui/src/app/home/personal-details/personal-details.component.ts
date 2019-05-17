import { Component, Input, OnInit } from '@angular/core';
import { AuthService } from '../../shared/service/auth/auth.service';
import { User } from '../../shared/service/user/user';
import { Transaction } from '../../shared/service/user/transaction';

@Component({
    selector: 'app-personal-details',
    templateUrl: './personal-details.component.html',
    styleUrls: ['./personal-details.component.scss']
})
export class PersonalDetailsComponent implements OnInit {

    @Input() user: User;
    displayTransfer = false;

    transaction: Transaction;

    constructor(public authService: AuthService) {
    }

    ngOnInit() {
    }

    transfer(): void {
        this.displayTransfer = true;
        this.transaction = undefined;
    }

    onTransferred(transaction: Transaction): void {
        this.displayTransfer = false;
        this.transaction = transaction;
    }
}
