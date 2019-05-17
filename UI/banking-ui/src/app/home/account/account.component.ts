import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Account } from '../../shared/service/user/account';
import { UserService } from '../../shared/service/user/user.service';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { Transaction } from '../../shared/service/user/transaction';

@Component({
    selector: 'app-account',
    templateUrl: './account.component.html',
    styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit, OnDestroy {
    private destroyed = new Subject<void>();

    @Input() account: Account;

    transactions: Transaction[] = [];
    noTransactionsMessage = undefined;

    constructor(private userService: UserService) {
    }

    ngOnInit() {
    }

    getTransactions() {
        this.userService.getTransactions(this.account.id)
            .pipe(takeUntil(this.destroyed))
            .subscribe(transactions => {
                    this.transactions = transactions;
                    this.noTransactionsMessage = transactions.length > 0 ? undefined : 'There are no transactions';
                }
            );
    }

    ngOnDestroy(): void {
        this.destroyed.next();
        this.destroyed.complete();
    }
}
