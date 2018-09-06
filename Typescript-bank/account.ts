export class Account {

    balance: number;
    type: string;

    constructor(balance:number, type:string){
        this.balance = balance;
        this.type = type;111
    }

    withdraw(amount:number){
        this.balance -= amount;
    }

    deposit(amount:number){
        this.balance += amount;
    }
1}