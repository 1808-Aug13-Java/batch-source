import { User } from "./revature9-06user";
import { Account } from "./revature9-06accounts";

let users: User[] = [
    new User('Carl', 'password36'),
    new User('John', 'pass1'),
    new User('Bill', 'password2')
]

//2. 
for(let user in users){
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
// these errrors are ok
const readline = require('readline');

const r1 = readline.createInterface({
    input: process.stdin,
    output: process.stdout
})

let username:string;
let password:string;
let loggedUser:User;

getUsername();

function getUsername(){
    r1.question("Input your username:", (answer) =>{
        username = answer;
        getPassword();
    })
}

function getPassword(){
    r1.question("Input your password:", (answer) =>{
        password = answer;
        login();
    })
}

function login(){
    loggedUser = users.filter( (user: User) => 
                user.login(username, password) )[0];
    if(loggedUser){
        console.log("welcome to the bank");
        
        loggedUser.getAccount(0);
    }
    else{
        console.log("invalid credentials. try again");
        getUsername();
    }   
    
}