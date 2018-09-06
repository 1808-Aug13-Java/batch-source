"use strict";
exports.__esModule = true;
var user_1 = require("./user");
var account_1 = require("./account");
var users = [
    new user_1.User('Carl', 'password36'),
    new user_1.User('John', 'pass1'),
    new user_1.User('Bill', 'h*^352xksh')
];
/*
for (let user of users) {
    console.log(user.username + ", " + user.password);
}*/
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
    rl.question("Input your username: ", function (answer) {
        username = answer;
        getPassword();
    });
}
function getPassword() {
    rl.question("Input your password: ", function (answer) {
        password = answer;
        login();
    });
}
function login() {
    loggedUser = users.filter(function (user) { return user.login(username, password); })[0];
    if (loggedUser) {
        console.log("welcome to the bank");
        getAccount();
    }
    else {
        console.log("invalid credentials, please try again");
        getUsername();
    }
}
var account;
function getAccount() {
    rl.question("Which account would you like to make a transaction on? Enter 'c' for checking and 's' for saving.", function (answer) {
        if (answer === 'c') {
            console.log("checking");
            // account = loggedUser.accounts.filter((account: Account)=>account.type==="checking")[0];
            account = loggedUser.accounts[0];
            makeTransaction();
        }
        else if (answer === 's') {
            console.log("saving");
            // account = loggedUser.accounts.filter((account: Account)=>account.type==="saving")[0];
            account = loggedUser.accounts[1];
            makeTransaction();
        }
        else {
            console.log("invalid account type, please try again");
        }
    });
}
var transaction;
var amount;
function makeTransaction() {
    rl.question("How much would you like to deposit or withdraw", function (answer) {
        amount = answer;
    });
    rl.question("Enter 'd' for deposit or 'w' for withdrawal", function (answer) {
        transaction = answer;
        if (transaction === 'd') {
            account.deposit(amount);
            console.log("Deposited. Your balance is now $" + account.balance);
        }
        else if (transaction === 'w') {
            account.widthdraw(amount);
            console.log("Withdrawn. Your balance is now $" + account.balance);
        }
        else {
            console.log("invalid transaction command, please try again");
        }
    });
}
