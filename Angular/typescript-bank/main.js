"use strict";
exports.__esModule = true;
var user_1 = require("./user");
var account_1 = require("./account");
var users = [
    new user_1.User('Carl', 'password'),
    new user_1.User('John', 'pass1'),
    new user_1.User('Bill', 'dab420')
];
// for (let user of users) {
//     console.log(user.username + ", " + user.password);
// }
// console.log("Hello from main");
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
    new account_1.Account(230, 'saving')
];
// Take user input, will show errors in ts but node can understand
var readline = require('readline');
var rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});
var username;
var password;
var loggedUser;
var selectAccount;
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
        console.log("welcome to the bank");
        accountMenu();
    }
    else {
        console.log("invalid credentials, try again");
        getUsername();
    }
}
function accountMenu() {
    rl.question("\n1. Checking\n2. Saving\n3. Logout\n", function (answer) {
        if (answer == 1) {
            selectAccount = loggedUser.getAccount(0);
            menu();
        }
        else if (answer == 2) {
            selectAccount = loggedUser.getAccount(1);
            menu();
        }
        else if (answer == 3) {
            getUsername();
        }
        else {
            console.log("\nEnter valid command");
            accountMenu();
        }
    });
}
function menu() {
    rl.question("\n1. Withdraw\n2. Deposit\n3. View Balance\n4. Select Account\n", function (answer) {
        if (answer == 1) {
            withdraw();
        }
        else if (answer == 2) {
            deposit();
        }
        else if (answer == 3) {
            view();
        }
        else if (answer == 4) {
            accountMenu();
        }
        else {
            console.log("\nEnter valid command");
            menu();
        }
    });
}
function withdraw() {
    rl.question("\nEnter amount to withdraw:", function (answer) {
        selectAccount.withdraw(answer);
        menu();
    });
}
function deposit() {
    rl.question("\nEnter amount to deposit:", function (answer) {
        selectAccount.deposit(answer);
        menu();
    });
}
function view() {
    console.log("\nCurrent balance: " + selectAccount.currBalance());
    menu();
}
