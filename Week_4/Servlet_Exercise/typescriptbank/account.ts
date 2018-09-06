// use export keyword to be able to use this class anywhere else
export class Account {

    balance: number;
    type: string;

    constructor(balance:number, type:string) {
        this.balance = balance;
        this.type = type;
    }

    withdraw(amount:number) {
        this.balance -= amount;
    }
 
    deposit(amount:number) {
        this.balance += amount;
    }
}