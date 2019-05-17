import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './user';
import { map } from 'rxjs/operators';
import { AuthService } from '../auth/auth.service';
import { Transaction } from './transaction';

@Injectable({
    providedIn: 'root'
})
export class UserService {
    private readonly BASE_URL = '/banking/';

    constructor(private http: HttpClient, private auth: AuthService) {
    }

    getUser(): Observable<User> {
        const URL = this.BASE_URL + 'users/' + this.auth.userId;

        return this.http.get(URL, { headers: this.getJwtHeader() })
            .pipe(
                map((user: User) => user)
            );
    }

    getTransactions(accountId: string): Observable<Transaction[]> {
        const URL = this.BASE_URL + 'users/' + this.auth.userId + '/accounts/' + accountId + '/transactions';
        return this.http.get(URL, { headers: this.getJwtHeader() })
            .pipe(
                map((transactions: any) => transactions.transactions)
            );
    }

    private getJwtHeader(): HttpHeaders {
        const jwt = this.auth.token;
        return new HttpHeaders(jwt ? jwt : {});
    }

    transfer(accountFrom: string, accountTo: string, amount: number): Observable<Transaction> {
        const URL = this.BASE_URL + 'users/' + this.auth.userId + '/accounts/transactions';
        const transaction: Transaction = {
            fromAccountNumber: accountFrom,
            toAccountNumber: accountTo,
            amount: amount
        };
        return this.http.post(URL, transaction, { headers: this.getJwtHeader() })
            .pipe(
                map((response: Transaction) => response)
            );
    }
}
