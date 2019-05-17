import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home.component';
import { PersonalDetailsComponent } from './personal-details/personal-details.component';
import { TransferComponent } from './transfer/transfer.component';
import { AccountComponent } from './account/account.component';
import { TransactionComponent } from './transaction/transaction.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ErrorMessageModule } from '../shared/error-message/error-message.module';

@NgModule({
    declarations: [HomeComponent, PersonalDetailsComponent, TransferComponent, AccountComponent, TransactionComponent],
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        ErrorMessageModule
    ]
})
export class HomeModule {
}
