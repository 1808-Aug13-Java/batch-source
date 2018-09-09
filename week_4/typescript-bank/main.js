"use strict";
exports.__esModule = true;
var user_1 = require("./user");
var account_1 = require("./account");
var users = [
    new user_1.User('Carl', 'password36'),
    new user_1.User('John', 'pass1'),
    new user_1.User('Bill', 'h*^352xksh')
];
// for (let user in users) {
//     console.log(user.username+ ", " + user.password);
// }
users[0].accounts = [
    new account_1.Account(500, "checking"),
    new account_1.Account(800, 'saving')
];
users[1].accounts = [
    new account_1.Account(325, "checking"),
    new account_1.Account(250, 'saving')
];
users[2].accounts = [
    new account_1.Account(450, "checking"),
    new account_1.Account(850, 'saving')
];
var readline = require('readline');
var rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});
console.log("Hello from main");
var username;
var password;
var loggedUser;
var checking;
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
        getCheckingOrSaving();
    }
    else {
        console.log("Invalid credentials, please try again");
        getUsername();
    }
}
function getCheckingOrSaving() {
    rl.question("Checking or savings?", function (answer) {
        checking = answer;
        if (checking === "Checking" || checking === "checking") {
            // loggeduser = users[0].accounts((account: Account) => getCheckingOrSaving(checking))
            console.log("Enter amount to deposit into CHECKING account: ");
        }
        else if (checking === "Saving" || checking === "saving") {
            console.log("Enter amount to deposit into SAVINGS account: ");
        }
        else {
            console.log("invalid account type.");
        }
        console.log("working");
    });
}
for (var user in users) {
    console.log(user);
}
