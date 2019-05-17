export interface User {
    id: string;
    username: string;
    firstName: string;
    lastName: string;
    phone: string;
    email: string;
    createdAt: string;
    updatedAt: string;
    accounts: Account[];
}
