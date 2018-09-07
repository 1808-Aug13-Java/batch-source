import{Account} from './account';

export class User{
    username: string;
    password: string;
    accounts: Account[] = [];

        constructor(user: string, pass: string,){
            this.username = user;
            this.password = pass;
        }
        login(user:string, pass: string){
            return this.username === user && this.password === pass;
        }
        getAccount(index: number): Account{
            return this.accounts[index];
        }
}