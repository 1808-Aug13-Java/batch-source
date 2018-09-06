"use strict";
exports.__esModule = true;
var user_1 = require("./user");
var account_1 = require("./account");
var users = [
    new user_1.User('Carl', 'password36'),
    new user_1.User('John', 'pass1'),
    new user_1.User('Bill', 'h*^352xksh')
];
for (var user in users) {
    console.log(user);
}
users[0].accounts = [
    new account_1.Account(500, 'checking'),
    new account_1.Account(800, 'saving')
];
users[1].accounts = [
    new account_1.Account(500, 'checking'),
    new account_1.Account(800, 'saving')
];
users[2].accounts = [
    new account_1.Account(500, 'checking'),
    new account_1.Account(800, 'saving')
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
    }
    else {
        console.log("invalid credentials, pelase try again");
        getUsername();
    }
}
