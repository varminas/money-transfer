import { Component, EventEmitter, OnDestroy, OnInit, Output } from '@angular/core';
import { Transaction } from '../../shared/service/user/transaction';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../../shared/service/user/user.service';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

@Component({
    selector: 'app-transfer',
    templateUrl: './transfer.component.html',
    styleUrls: ['./transfer.component.scss']
})
export class TransferComponent implements OnInit, OnDestroy {
    private destroyed = new Subject<void>();

    form: FormGroup;

    errorMessage: string = undefined;

    @Output() transferred = new EventEmitter<Transaction>();

    constructor(private userService: UserService) {
    }

    ngOnInit() {
        this.form = new FormGroup({
            'accountFrom': new FormControl('', [Validators.required, Validators.minLength(5)]),
            'accountTo': new FormControl('', [Validators.required, Validators.minLength(5)]),
            'amount': new FormControl('', [Validators.required])
        });
    }

    ngOnDestroy(): void {
        this.destroyed.next();
        this.destroyed.complete();
    }

    create(): void {
        if (!this.isFormValid()) {
            return;
        }

        const accountFrom = this.form.getRawValue()['accountFrom'];
        const accountTo = this.form.getRawValue()['accountTo'];
        const amount = this.form.getRawValue()['amount'];

        this.userService.transfer(accountFrom, accountTo, parseInt(amount, 10))
            .pipe(takeUntil(this.destroyed))
            .subscribe(
                transaction => {
                    this.transferred.emit(transaction);
                },
                error => {
                    this.errorMessage = error.error.message || 'Error occurred while making transfer';
                }
            );
    }

    get accountFromControl(): AbstractControl {
        return this.form.get('accountFrom');
    }

    get accountToControl(): AbstractControl {
        return this.form.get('accountTo');
    }

    get amountControl(): AbstractControl {
        return this.form.get('amount');
    }

    private isFormValid(): boolean {
        return this.form.valid;
    }
}
