import {User} from './user';
import {Account} from './account';

let users: User[] = [
    new User('Carl', 'password36'),
    new User('John', 'pass1'),
    new User('Bill', 'h*^352xksh')
]


// for(let user of users){
//     console.log(user.username+", "+user.password);
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
let change: number;

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
    loggedUser = users.filter((user: User)=>user.login(username,password))[0];
    if (loggedUser){
        console.log("welcome to the bank");
        askUser();
    } else {
        console.log("invalid credentials, please try again");
        getUsername();
    }
}

function askUser(){
    rl.question("What would you like to do: 1. Deposit or 2. Withdraw?", (answer)=>{
        if (answer == 1){
            console.log("you have chosen deposit");
            deposit();
        } else if ( answer ==2){
            console.log("you have chosen withdraw")
            // withdraw();
        } else {
            console.log("Invalid input");
            askUser();
        }
    })
}

function deposit(){
    
}