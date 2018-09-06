"use strict";
exports.__esModule = true;
var user_1 = require("./user");
var accounts_1 = require("./accounts");
//create some users
var users = [
    new user_1.User("Carl", "password36"),
    new user_1.User("John", "pass21"),
    new user_1.User("Bill", "h**352xksh")
];
//print users to console
// for(let user of users){
// console.log(user.username + ", " + user.password);
// }
// add accounts associated with each user
users[0].accounts = [
    new accounts_1.Account(500, "checking"),
    new accounts_1.Account(800, "saving")
];
users[1].accounts = [
    new accounts_1.Account(450, "checking"),
    new accounts_1.Account(850, "saving")
];
users[2].accounts = [
    new accounts_1.Account(325, "checking"),
    new accounts_1.Account(250, "saving")
];
var readline = require("readline"); // this is almost like our scanner in java
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
    loggedUser = users.filter(function (user) { return user.login(username, password); })[0]; // inline function, takes user as parameter; we want to use the predicate to check pswd and usrnm
    if (loggedUser) {
        console.log("welcome to the bank");
        menu();
    }
    else {
        console.log("invalid credentials, please try again");
        getUsername();
    }
}
// ------------------------------------------------------------
//make ability to withdraw and deposit
function menu() {
    rl.question("Type 1 if you want to deposit and press 2 if you want to withdraw: ", function (answer) {
        if (answer == '1') {
            toDeposit();
        }
        if (answer == '2') {
            toWithdraw();
        }
        else {
            console.log("invalid input, please try again");
            getUsername();
        }
    });
}
function toWithdraw() {
    rl.question("Type how much you want to withdraw: ", function (answer) {
        loggedUser[0].withdraw(parseInt(answer));
    });
    //console.log("Your balance is now: " + loggedUser[0].balance);
}
function toDeposit() {
    rl.question("Type how much you want to withdraw: ", function (answer) {
        loggedUser[0].deposit(parseInt(answer));
    });
    //console.log("Your balance is now: " + loggedUser[0].balance);
}
