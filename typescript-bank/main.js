"use strict";
exports.__esModule = true;
var user_1 = require("./user");
var account_1 = require("./account");
var users = [
    new user_1.User('Carl', 'password36'),
    new user_1.User('Jon', 'pass1'),
    new user_1.User('Bill', 'pass2')
];
users[0].accounts = [
    new account_1.Account(500, 'checking'),
    new account_1.Account(800, 'saving')
];
users[1].accounts = [
    new account_1.Account(450, 'checking'),
    new account_1.Account(850, 'saving')
];
users[2].accounts = [
    new account_1.Account(325, 'checking'),
    new account_1.Account(250, 'saving')
];
var readline = require('readline');
var rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});
var username;
var password;
var loggedUser;
getUsername();
function getUsername() {
    rl.question("Input your username:", function (answer) {
        username = answer;
        getPassword();
    });
}
function getPassword() {
    rl.question("Input your password:", function (answer) {
        password = answer;
        login();
    });
}
function login() {
    loggedUser = users.filter(function (user) { return user.login(username, password); })[0];
    if (loggedUser) {
        console.log("Welcome to the bank");
        transactionType();
    }
    else {
        console.log("Invalid credentials. Please try again.");
        getUsername();
    }
}
function transactionType() {
    rl.question("Input transaction type (deposit or withdraw):", function (answer) {
        switch (answer) {
            case "deposit":
                depositDollars();
                break;
            case "withdraw":
                withdrawDollars();
                break;
            default:
                console.log("You must type either deposit or withdraw. Try again.");
                transactionType();
        }
    });
}
function depositDollars() {
    console.log("DEPOSIT");
}
function withdrawDollars() {
    console.log("WITHDRAW");
}
/*for(let user of users){
    console.log(user.username + ", " + user.password);
}*/
//console.log("hello from main");
