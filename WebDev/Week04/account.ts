

// Example of a class. 
// To use outside of this file, use the export keyword. 

export class Account {

	balance: number;
	type: string;

	constructor(balance:number, type:string) {
		this.balance = balance;
		this.type = type;
	}

	// Withdraws money from the account. 
	withdraw(amount: number): void {
		this.balance -= amount;
	}

	deposit(amount: number): void {
		this.balance += amount;
	}

} // end of class Accout