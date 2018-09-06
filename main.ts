import {User} from "./user";
import {Account} from "./accounts"

//create some users
let users: User[] = [
    new User("Carl", "password36"),
    new User("John", "pass21"),
    new User("Bill", "h**352xksh")
]

//print users to console
// for(let user of users){
// console.log(user.username + ", " + user.password);
// }

// add accounts associated with each user
users[0].accounts = [
    new Account(500, "checking"),
    new Account(800, "saving")
]

users[1].accounts = [
    new Account(450, "checking"),
    new Account(850, "saving")
]

users[2].accounts = [
    new Account(325, "checking"),
    new Account(250, "saving")
]

const readline = require("readline"); // this is almost like our scanner in java
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
})

let username: string;
let password: string;
let loggedUser: User;

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
    loggedUser = users.filter((user: User)=>user.login(username, password))[0]; // inline function, takes user as parameter; we want to use the predicate to check pswd and usrnm
    if (loggedUser) {
        console.log("welcome to the bank");
        menu();
    } else {
        console.log("invalid credentials, please try again");
        getUsername();
    }
}

// ------------------------------------------------------------

//make ability to withdraw and deposit

function menu(){
    rl.question("Type 1 if you want to deposit and press 2 if you want to withdraw: ", (answer)=>{
        if (answer == '1'){
            toDeposit();
        }
        if (answer == '2'){
            toWithdraw();
        }
        else {
            console.log("invalid input, please try again");
            getUsername();
        }
    })
}

function toWithdraw(){
    rl.question("Type how much you want to withdraw: ", (answer)=>{
        loggedUser[0].withdraw(parseInt(answer));
    })
    //console.log("Your balance is now: " + loggedUser[0].balance);
}

function toDeposit(){
    rl.question("Type how much you want to withdraw: ", (answer)=>{
        loggedUser[0].deposit(parseInt(answer));
    })
    //console.log("Your balance is now: " + loggedUser[0].balance);
}