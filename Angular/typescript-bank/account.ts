// Need to include "export" keyword to use it in other files
export class Account {
    balance: number;
    type: string;

    constructor(b: number, t: string){
        this.balance = b;
        this.type = t;
    }

    withdraw(amount: number){
        this.balance -= amount;
    }

    deposit(amount: number){
        this.balance += amount;
    }

    currBalance(){
        return this.balance;
    }
}