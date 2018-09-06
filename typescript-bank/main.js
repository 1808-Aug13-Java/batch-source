"use strict";
exports.__esModule = true;
var user_1 = require("./user");
var account_1 = require("./account");
var users = [
    new user_1.User('Carl', 'password36'),
    new user_1.User('John', 'pass1'),
    new user_1.User('Bill', 'h*^352xksh')
];
for (var _i = 0, users_1 = users; _i < users_1.length; _i++) {
    var user = users_1[_i];
    console.log(user.username + ", " + user.password);
}
//transpile?
console.log("Hello from main");
users[0].accounts = [
    new account_1.Account(500, "checking"),
    new account_1.Account(800, 'saving')
];
users[1].accounts = [
    new account_1.Account(450, "checking"),
    new account_1.Account(850, 'saving')
];
users[2].accounts = [
    new account_1.Account(325, "checking"),
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
        console.log("welcome to the bank");
    }
    else {
        console.log("invalid credentials, please try again");
        getUsername();
    }
}
function options() {
    rl.question("Select an option:\n1)Deposit\n2)Withdraw\n3)Display Balance", function (input) {
        switch (input) {
            case 1:
                deposit();
            case 2:
                withdraw();
            case 3:
                display();
        }
    });
}
function deposit() {
    var accountType;
    rl.question("Please specify which an account to deposit:\n1)Checking\n2)Savings", function (answer) {
        accountType = answer;
    });
    switch (accountType) {
        case "1":
            rl.question("Please enter a deposit amount:", function (answer) {
                loggedUser.accounts[0].deposit(answer);
            });
        case "2":
            rl.question("Please enter a deposit amount:", function (answer) {
                loggedUser.accounts[1].deposit(answer);
            });
    }
}
function withdraw() {
    var accountType;
    rl.question("Please specify which an account to withdraw:\n1)Checking\n2)Savings", function (answer) {
        accountType = answer;
    });
    switch (accountType) {
        case "1":
            rl.question("Please enter withdrawal amount:", function (answer) {
                loggedUser.accounts[0].withdraw(answer);
            });
        case "2":
            rl.question("Please enter a withdrawal amount:", function (answer) {
                loggedUser.accounts[1].withdraw(answer);
            });
    }
}
function display() {
    var accountType;
    rl.question("Please specify which an account to display balance:\n1)Checking\n2)Savings", function (answer) {
        accountType = answer;
    });
    switch (accountType) {
        case "1":
            console.log(loggedUser.accounts[0].balance);
        case "2":
            console.log(loggedUser.accounts[1].balance);
    }
}
getUsername();
options();
