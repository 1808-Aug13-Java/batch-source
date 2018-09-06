import {User} from './user';
import {Account} from './account';

let users: User[] = [
    new User('Carl', 'password'),
    new User('John', 'pass1'),
    new User('Bill', 'dab420')
]

// for (let user of users) {
//     console.log(user.username + ", " + user.password);
// }
// console.log("Hello from main");

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
    new Account(230, 'saving')
]

// Take user input, will show errors in ts but node can understand
const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
})

let username: string;
let password: string;
let loggedUser: User;
let selectAccount: Account;

getUsername();

function getUsername(){
    rl.question("Input your username:", (answer)=>{
        username = answer;
        getPassword();
    })
}

function getPassword(){
    rl.question("Input your password:", (answer)=>{
        password = answer;
        login();
    })
}

function login(){
    loggedUser = users.filter((user: User) => user.login(username, password))[0];
    if (loggedUser){
        console.log("welcome to the bank");
        accountMenu();
    } else {
        console.log("invalid credentials, try again");
        getUsername();
    }
}

function accountMenu(){
    rl.question("\n1. Checking\n2. Saving\n3. Logout\n", (answer)=>{
        if(answer == 1){
            selectAccount = loggedUser.getAccount(0);
            menu();
        } else if (answer == 2) {
            selectAccount = loggedUser.getAccount(1);
            menu();
        } else if (answer == 3) {
            getUsername();
        } else {
            console.log("\nEnter valid command");
            accountMenu();
        }
    })
}

function menu(){
    rl.question("\n1. Withdraw\n2. Deposit\n3. View Balance\n4. Select Account\n", (answer)=>{
        if(answer == 1){
            withdraw();
        } else if (answer == 2){
            deposit();
        } else if (answer == 3){
            view();
        } else if (answer == 4){
            accountMenu();
        } else {
            console.log("\nEnter valid command");
            menu();
        }
    })
}

function withdraw(){
    rl.question("\nEnter amount to withdraw:", (answer)=>{
        selectAccount.withdraw(answer);
        menu();
    })
}

function deposit(){
    rl.question("\nEnter amount to deposit:", (answer)=>{
        selectAccount.deposit(answer);
        menu();
    })
}

function view(){
    console.log("\nCurrent balance: " + selectAccount.currBalance());
    menu();
}