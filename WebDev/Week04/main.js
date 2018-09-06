"use strict";
exports.__esModule = true;
var user_1 = require("./user");
var account_1 = require("./account");
var users = [
    new user_1.User('Carl', "pa$$w0rd"),
    new user_1.User('John', 'pass1'),
    new user_1.User('Bill', 'h5^124@5ax47')
];
for (var user in users) {
    console.log(user);
}
users[0].accounts = [
    new account_1.Account(500, 'checking'),
    new account_1.Account(600, 'saving')
];
users[1].accounts = [
    new account_1.Account(325, 'checking'),
    new account_1.Account(425, 'saving')
];
users[2].accounts = [
    new account_1.Account(7, 'checking'),
    new account_1.Account(7, 'saving')
];
// These errors are fine in this specific case, as they work in 
// node.js to get input from console. 
var readline = require('readline');
var rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});
var username;
var password;
var loggedUser;
function getUsername() {
    // Get user input from the console, and perform a callback with the input
    rl.question("Input Your Username: ", function (answer) {
        username = answer;
        getPassword();
    });
}
function getPassword() {
    rl.question("Input Your Password: ", function (answer) {
        password = answer;
        login();
    });
}
function login() {
    // Uses the filter function of the users array to make a new array with only 
    // the users whose 'login' functions return true. 
    loggedUser = users.filter(function (user) { return user.login(username, password); })[0];
    // for (let user of users) {
    // 	if (user.login(username, password)) {
    // 		loggedUser = user;
    // 		break;
    // 	}
    // }
    if (loggedUser) {
        console.log("Welcome to the bank!");
        getLoggedInInput();
    }
    else {
        console.log("Invalid Credentials. Try Again");
        getUsername();
    }
}
function getLoggedInInput() {
    console.log("Type 'h' for help.");
    rl.question("Enter Query>: ", function (input) {
        // When we receive input, if it is not a logout command, pass it to the input processor. 
        // Then repeat the process, until the user logs out. 
        if (input !== "logout") {
            loggedInProcess(input);
            getLoggedInInput();
        }
        else {
            console.log("Thanks For Banking!");
        }
    });
}
// This function holds the state loop for when a user is logged in. 
function loggedInProcess(input) {
    var quit = false;
    // Used to parse the input
    var tokens;
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
