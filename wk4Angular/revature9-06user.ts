import { Account } from "./revature9-06accounts";

export class User{
    //1.
    username: string;
    password: string;
    accounts: Account[] = [];  //2.now lets import Account

    //3.
    constructor(user: string, pass: string){
        this.username = user;
        this.password = pass;
    }
    //4. make login and getAccount()
    login(user: string, pass: string){
        return this.username===user && this.password===pass;
    }

    getAccount(index: number): Account{
        return this.accounts[index];
    //5. go to main.ts
    }
}