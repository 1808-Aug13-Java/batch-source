"use strict";
exports.__esModule = true;
var user_1 = require("./user");
var account_1 = require("./account");
var users = [
    new user_1.User('Carl', 'password16'),
    new user_1.User('John', 'pass1'),
    new user_1.User('Bill', 'dope')
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
// for(let user in users){
//     console.log(user.username+" "+ user.password);
// }
var readline = require('readline');
var rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});
var username;
var password;
var loggedUser;
var workingAccount;
getUsername();
function getUsername() {
    rl.question("Input your question", function (answer) {
        username = answer;
        getPassword();
    });
}
function getPassword() {
    rl.question("input your password", function (answer) {
        password = answer;
        login();
    });
}
function login() {
    loggedUser = users.filter(function (user) { return user.login(username, password); })[0];
    if (loggedUser) {
        console.log("welcome to the bank");
        menu();
    }
    else {
        console.log("invalid credentials, please try again");
        getUsername();
    }
}
function menu() {
    // console.log("Please select the number for the transaction you would like to perform.")
    // console.log("1.withdraw from account");
    // console.log("2.Deposit to an account");
    rl.question("Choose an account, 1.Checking or 2.Savings ", function (answer) {
        var choice = answer;
        switch (choice) {
            case 1: {
                //withdraw
                // workingAccount = Account.filter()
                workingAccount = loggedUser.accounts[0];
                whichTrans();
            }
            case 2: {
                //deposit
                workingAccount = loggedUser.accounts[1];
                whichTrans();
            }
            default: {
                console.log("Please select a valid choice");
                menu();
            }
        }
    });
}
function whichTrans() {
    rl.question("Choose a transaction, 1.withdraw or 2.deposit", function (answer) {
        var choice = answer;
        switch (choice) {
            case 1: {
                workingAccount.withdraw(answer);
                menu();
            }
            case 2: {
                workingAccount.deposit(answer);
                menu();
            }
            default: {
                console.log("Please select a valid option");
                whichTrans();
            }
        }
    });
}
