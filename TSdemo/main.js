"use strict";
exports.__esModule = true;
var user_1 = require("./user");
var account_1 = require("./account");
var users = [
    new user_1.User('Carl', 'password36'),
    new user_1.User('John', 'pass1'),
    new user_1.User('Bill', '123')
];
//for(let user in users){
//     console.log(user.username+", "+user.password);
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
    new account_1.Account(220, 'checking'),
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
    rl.question("Input your password: ", function (answer) {
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
    rl.question("Type 0 to deposite or 1 to withdraw from an account.", function (response) {
        if (response == '0') {
            Deposit();
        }
        if (response == '1') {
            Withdraw();
        }
        else {
            console.log("invalid input, please try again");
            getUsername();
        }
    });
}
function Withdraw() {
    rl.question("Type how much you want to withdraw: ", function (response) {
        loggedUser[0].withdraw(parseInt(response));
    });
}
function Deposit() {
    rl.question("Type how much you want to withdraw: ", function (response) {
        loggedUser[0].deposit(parseInt(response));
    });
}
