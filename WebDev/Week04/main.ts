import {User} from './user';
import {Account} from './account';

let users: User[] = [
	new User('Carl', "pa$$w0rd"),
	new User('John', 'pass1'),
	new User('Bill', 'h5^124@5ax47')
];


for (let user in users) {
	console.log(user);
}


users[0].accounts = [
	new Account(500, 'checking'),
	new Account(600, 'saving')
];
users[1].accounts = [
	new Account(325, 'checking'),
	new Account(425, 'saving')
];
users[2].accounts = [
	new Account(7, 'checking'),
	new Account(7, 'saving')
];

// These errors are fine in this specific case, as they work in 
// node.js to get input from console. 
const readline = require('readline');
const rl = readline.createInterface({
	input: process.stdin,
	output: process.stdout
});

let username: string;
let password: string;
let loggedUser: User;

function getUsername() {
	// Get user input from the console, and perform a callback with the input
	rl.question("Input Your Username: ", (answer)=>{
		username = answer;
		getPassword();
	});
}

function getPassword() {
	rl.question("Input Your Password: ", (answer)=>{
		password = answer;
		login();
	});
}

function login() {
	// Uses the filter function of the users array to make a new array with only 
	// the users whose 'login' functions return true. 
	loggedUser = users.filter((user: User): boolean=>{return user.login(username, password)})[0];

	// for (let user of users) {
	// 	if (user.login(username, password)) {
	// 		loggedUser = user;
	// 		break;
	// 	}
	// }
	if (loggedUser) {
		console.log("Welcome to the bank!");
		getLoggedInInput();
	} else {
		console.log("Invalid Credentials. Try Again");
		getUsername();
	}
}


function getLoggedInInput() {
	console.log("Type 'h' for help.");

	rl.question("Enter Query>: ", (input)=>{
		// When we receive input, if it is not a logout command, pass it to the input processor. 
		// Then repeat the process, until the user logs out. 
		if (input !== "logout") {
			loggedInProcess(input);
			getLoggedInInput();
		} else {
			console.log("Thanks For Banking!");
		}
	});
}

// This function holds the state loop for when a user is logged in. 
function loggedInProcess(input: string): void {
	let quit:boolean = false;

	// Used to parse the input
	let tokens:string[];

	// If the input is 'h', print help. 
	if (input == 'h' || input == 'help') {
		console.log("To deposit, type 'deposit <account> <amount>");
		console.log("To withdraw, type 'witdraw <account> <amount>");
		console.log("To log out, type 'logout'");
	}
	// Otherwise, if the input is 'logout', log out. 
	else if (input == 'logout') {
		console.log("Thanks for banking here!");
		quit = true;
	}
	// Otherwise, parse the input and take action accordingly
	else {
		tokens = input.split(" ");

		// Validate that we have the proper length of tokens. 
		if (tokens.length !== 3) {
			console.log("Invalid input. ");
			return;
		}
	}
} // end of loggedInState




getUsername();
