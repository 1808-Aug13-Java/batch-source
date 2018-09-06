export class Account {
    balance: number;
    type: string;

    constructor(balance: number, type: string) {
        this.balance = balance;
        this.type = type;
    }

    widthdraw(amount:number) {
        this.balance -= amount;
    }

    deposit(amount:number) {
        this.balance += amount;
    }
}