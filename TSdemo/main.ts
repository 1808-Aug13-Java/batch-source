import {User} from './user';
import {Account} from './account';

let users: User[] = [
    new User('Carl', 'password36'),
    new User('John', 'pass1'),
    new User('Bill','123')
]

//for(let user in users){
//     console.log(user.username+", "+user.password);
// }
// console.log("Hello from main")

users[0].accounts = [
    new Account(500, 'checking'),
    new Account(800, 'saving')
]
users[1].accounts = [
    new Account(450, 'checking'),
    new Account(850, 'saving')
]
users[2].accounts = [
    new Account(220, 'checking'),
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

function getUsername(){
    rl.question("Input your username:", (answer)=>{
        username = answer;
        getPassword();
    })
}

function getPassword(){
    rl.question("Input your password: ",(answer)=>{
        password = answer;
        login();
    })
}

function login(){
    loggedUser = users.filter((user:User)=>user.login(username, password))[0];
    if (loggedUser){
        console.log("welcome to the bank");
        Menu();
    }else{
        console.log("invalid credentials, please try again");
        getUsername();
    }
}

function Menu() {
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