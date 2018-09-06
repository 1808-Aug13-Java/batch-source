import {User} from './user';
import {Account} from './account';

let users: User[] = [
    new User('Carl', 'password36'),
    new User('Jon', 'pass1'),
    new User('Bill', 'pass2')
]

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
const rl= readline.createInterface({
    input: process.stdin,
    output: process.stdout
})

let username: string;
let password: string;
let loggedUser: User;

getUsername();

function getUsername(){
    rl.question("Input your username:", (answer)=> {
        username = answer;
        getPassword();
    })
}

function getPassword(){
    rl.question("Input your password:", (answer)=> {
        password = answer;
        login();
    })
}

function login() {
    loggedUser = users.filter((user: User)=>user.login(username,password))[0];
    if(loggedUser){
        console.log("Welcome to the bank");
        selectAccount();
    } else {
        console.log("Invalid credentials. Please try again.");
        getUsername();
    }
}

function selectAccount() {
    rl.question("Input account you would like to access (checking or savings):", (answer)=>{
        switch(answer){
            case "checking" :
            //stuff
            transactionType();    
            break;
            case "savings" :
            //stuff
            transactionType();
            break;
            default :
            console.log("You must input either checking or savings. Please try again.");
            selectAccount();
        }
    })

}

function transactionType() {
    rl.question("Input transaction type (deposit or withdraw):", (answer)=>{
        switch(answer) {
            case "deposit" :
                depositDollars();
                break;
            case "withdraw" :
                withdrawDollars();
                break;
            default :
            console.log("You must type either deposit or withdraw. Try again.");
            transactionType();
        }
    })
}

function depositDollars() {
    //console.log("DEPOSIT");
}

function withdrawDollars() {
    console.log("WITHDRAW");

}
/*for(let user of users){
    console.log(user.username + ", " + user.password);
}*/

//console.log("hello from main");