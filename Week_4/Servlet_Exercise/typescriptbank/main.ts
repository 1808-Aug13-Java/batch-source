import {User} from './user';
import {Account} from './account';

let users: User[] = [
    new User('Carl', 'password36'),
    new User('John', 'pass1'),
    new User('Bill', 'h*^352xksh')
]

//for(let user of users) {
//    console.log(user.username+", " +user.password);
//}
//console.log("Hello from main")

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

const readline = require('readline');// typescript does not recognize thiss, but its ok
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
})

let username: string;
let password: string;
let input : number;
let loggedUser: User;

let user = getUsername();
whatYouWant(loggedUser);
function whatYouWant(value:User) {
    rl.question("Please enter a number \n deposit(1) \n withdraw(2) \n exit (0) \nEnter:",(answer)=> {
        if((answer != 1) && (answer != 2) && (answer != 0)) {
            whatYouWant(value);
        }
        input = answer;
        if(input == 2) {
            withdraw(value);
        }
        if(input == 1) {
            deposit(value);
        }
    })
}
function getUsername() {
    rl.question("Input your username:",(answer)=> {
        username = answer;
        getPassword();
    })// use to read line of input
}

function getPassword(){
    rl.question("Input your password:", (answer)=> {
        password = answer;
        login();

    })
}

function login(): User{
    loggedUser = users.filter((user:User)=>user.login(username,password))[0];
    if(loggedUser) {
        console.log("welcome to the bank");
    }
    else {
        console.log("invalid credentials, please try again");
        getUsername();
    }
    console.log(loggedUser);
    return loggedUser;
}

function withdraw(value:User){
    rl.question("Please enter which account you want to withdraw from, Checking(0), Savings(1):", (answer:number)=> {
        if((answer != 0) && (answer != 1)) {
            withdraw(value);
        }
        else{
            let acc = answer;
            rl.question("Please enter how much you want to withdraw from checking: ", (answer:number)=> {
                if(answer )               
            })
        }
        if(answer == 0) {
            value[0].account 
        }
        value[0].account
        login();

    })
}
function deposit(value:User){

}
