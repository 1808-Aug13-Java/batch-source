import {User} from './user';
import {Account} from './account';

let users: User[] = [
    new User('Carl', 'password36'),
    new User('John', 'pass1'),
    new User('Bill', 'h*^352xksh')
]

/*
for (let user of users) {
    console.log(user.username + ", " + user.password);
}*/

users[0].accounts = [
    new Account(500, 'checking'),
    new Account(800, 'saving')
]

users[1].accounts = [
    new Account(450, 'checking'),
    new Account(850, 'saving')
]

users[2].accounts = [
    new Account(325, 'checking'),
    new Account(250, 'saving')
]

const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
})

let username: string;
let password: string;
let loggedUser: User;

getUsername();

function getUsername() {
    rl.question("Input your username: ", (answer)=>{
        username = answer;
        getPassword();
    })
}

function getPassword() {
    rl.question("Input your password: ", (answer)=>{
        password = answer;
        login();
    })
}

function login() {
    loggedUser = users.filter((user: User)=>user.login(username, password))[0];
    if (loggedUser) {
        console.log("welcome to the bank");
        getAccount();
    } else {
        console.log("invalid credentials, please try again");
        getUsername();
    }
}

let account: Account;
function getAccount() {
    rl.question("Which account would you like to make a transaction on? Enter 'c' for checking and 's' for saving.", (answer)=>{
        if (answer==='c') {
            console.log("checking");
            // account = loggedUser.accounts.filter((account: Account)=>account.type==="checking")[0];
            account = loggedUser.accounts[0];
            getAmount();
        } else if (answer==='s') {
            console.log("saving");
            // account = loggedUser.accounts.filter((account: Account)=>account.type==="saving")[0];
            account = loggedUser.accounts[1];
            getAmount();
        } else {
            console.log("invalid account type, please try again");
        }       
    })
}

let transaction: string;
let amount: number;
function getAmount() {
    rl.question("How much would you like to deposit or withdraw", (answer)=>{
        amount = answer;
    })
    makeTransaction();
}

function makeTransaction() {
    rl.question("Enter 'd' for deposit or 'w' for withdrawal", (answer)=>{
        transaction = answer;
        if (transaction==='d') {
            account.deposit(amount);
            console.log("Deposited. Your balance is now $"+account.balance);
        } else if (transaction==='w') {
            account.widthdraw(amount);
            console.log("Withdrawn. Your balance is now $"+account.balance);
        } else {
            console.log("invalid transaction command, please try again");
        }
    })
}