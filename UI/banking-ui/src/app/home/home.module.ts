import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home.component';
import { PersonalDetailsComponent } from './personal-details/personal-details.component';
import { AccountsComponent } from './accounts/accounts.component';
import { TransactionsComponent } from './transactions/transactions.component';
import { TransferComponent } from './transfer/transfer.component';

@NgModule({
    declarations: [HomeComponent, PersonalDetailsComponent, AccountsComponent, TransactionsComponent, TransferComponent],
    imports: [
        CommonModule
    ]
})
export class HomeModule {
}
