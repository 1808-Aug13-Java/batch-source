"use strict";
exports.__esModule = true;
var revature9_06user_1 = require("./revature9-06user");
var revature9_06accounts_1 = require("./revature9-06accounts");
var users = [
    new revature9_06user_1.User('Carl', 'password36'),
    new revature9_06user_1.User('John', 'pass1'),
    new revature9_06user_1.User('Bill', 'password2')
];
//2. 
for (var user in users) {
    console.log(user);
}
//3. go to console
/*
    tsc account.ts  <-- this "compiles" this file.
                    <-- it makes a .js version
    tsc user.ts
    tsc main.ts  <-- idk about this one, if u need

    node main   <-- to run
*/
users[0].accounts = [
    new revature9_06accounts_1.Account(500, 'checking'),
    new revature9_06accounts_1.Account(800, 'saving')
];
users[1].accounts = [
    new revature9_06accounts_1.Account(450, 'checking'),
    new revature9_06accounts_1.Account(850, 'saving')
];
users[2].accounts = [
    new revature9_06accounts_1.Account(325, 'checking'),
    new revature9_06accounts_1.Account(250, 'saving')
];
// these errrors are ok
var readline = require('readline');
var r1 = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});
var username;
var password;
var loggedUser;
getUsername();
function getUsername() {
    r1.question("Input your username:", function (answer) {
        username = answer;
        getPassword();
    });
}
function getPassword() {
    r1.question("Input your password:", function (answer) {
        password = answer;
        login();
    });
}
function login() {
    loggedUser = users.filter(function (user) {
        return user.login(username, password);
    })[0];
    if (loggedUser)
        console.log("welcome to the bank");
    else {
        console.log("invalid credentials. try again");
        getUsername();
    }
}
