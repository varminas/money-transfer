import { Component, Input, OnInit } from '@angular/core';
import { Transaction } from '../../shared/service/user/transaction';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.scss']
})
export class TransactionComponent implements OnInit {

  @Input() transaction: Transaction;

  constructor() { }

  ngOnInit() {
  }

}
