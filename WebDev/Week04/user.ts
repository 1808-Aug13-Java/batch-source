
// To use another class
import {Account} from './account';

// Another class example
export class User{
	username: string;
	password: string;
	accounts: Account[] = [];

	constructor(user: string, pass: string) {
		this.username = user;
		this.password = pass;
	}

	
	// Validate login
	login(user: string, pass: string): boolean {
		// console.log(this.username + "==" + user + " && " + this.password + "==" + pass);
		// console.log((this.username == user) + " && " + (this.password == pass));
		return this.username == user && this.password == pass;
	}

	getAccount(index: number): Account {
		return this.accounts[index];
	}
} // end of class User