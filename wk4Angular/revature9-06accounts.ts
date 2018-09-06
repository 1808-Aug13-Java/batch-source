export class Account{ //idk wat export does but we did
                    //it at the end.
    balance: number; 
    type: string;

    constructor(balance:number, type:string){
        this.balance = balance;
        this.type = type;
    }

    withdraw(amount:number){
        this.balance -= amount;
    }

    deposit(amount:number){
        this.balance += amount;
    }
}