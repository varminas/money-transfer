export interface Transaction {
    id?: string;
    amount: number;
    fromAccountNumber: string;
    toAccountNumber: string;
    createdAt?: string;
}
