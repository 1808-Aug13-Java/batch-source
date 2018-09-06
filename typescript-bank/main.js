"use strict";
exports.__esModule = true;
var user_1 = require("./user");
var account_1 = require("./account");
var users = [
    new user_1.User('Carl', 'password36'),
    new user_1.User('John', 'pass1'),
    new user_1.User('Bill', 'h*^352xksh')
];
// for (let user of users) {
//     console.log(user.username + " " + user.password);
// }
// console.log("Hello from main")
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
        displayBalances();
    }
    else {
        console.log("invalid credentials, please try again");
        getUsername();
    }
}
function displayBalances() {
    for (var i = 0; i < loggedUser.accounts.length; i++) {
        console.log(i + ": " + loggedUser.accounts[i].type + " " + parseFloat(loggedUser.accounts[i].balance).toFixed(2));
    }
    getTransactionType();
}
function getTransactionType() {
    var transaction;
    rl.question("Enter 1 to make a deposit, 2 to make a withdrawal, or 3 to exit:", function (answer) {
        transaction = answer;
        switch (transaction) {
            case "1":
                deposit();
                break;
            case "2":
                withdraw();
                break;
            case "3":
                console.log("Thank you for your business. Please come again.");
                process.exit(0);
            default:
                console.log("Invalid option.");
                displayBalances();
        }
    });
}
function withdraw() {
    var index;
    rl.question("Enter account index:", function (answer) {
        index = parseInt(answer);
        if (index >= 0 && index < loggedUser.accounts.length) {
            var amount_1;
            rl.question("Enter transaction amount:", function (answer) {
                amount_1 = parseFloat(answer);
                if (amount_1 >= 0.01) {
                    if (loggedUser.accounts[index].balance >= amount_1) {
                        loggedUser.accounts[index].withdraw(amount_1);
                        displayBalances();
                    }
                    else {
                        console.log("Cannot withdraw more than your balance.");
                        displayBalances();
                    }
                }
                else {
                    console.log("Invalid or negative amount.");
                    displayBalances();
                }
            });
        }
        else {
            console.log("Invalid index. Please try again.");
            displayBalances();
        }
    });
}
function deposit() {
    var index;
    rl.question("Enter account index:", function (answer) {
        index = parseInt(answer);
        if (index >= 0 && index < loggedUser.accounts.length) {
            var amount_2;
            rl.question("Enter transaction amount:", function (answer) {
                amount_2 = parseFloat(answer);
                if (amount_2 >= 0.01) {
                    loggedUser.accounts[index].deposit(amount_2);
                    displayBalances();
                }
                else {
                    console.log("Invalid or negative amount.");
                    displayBalances();
                }
            });
        }
        else {
            console.log("Invalid index. Please try again.");
            displayBalances();
        }
    });
}
getUsername();
