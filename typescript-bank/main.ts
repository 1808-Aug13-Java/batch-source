import {User} from './user';
import {Account} from './account';

let users: User[] = [
    new User('Carl','password36'),
    new User('John', 'pass1'),
    new User('Bill','h*^352xksh')
]

for(let user of users){
    console.log(user.username+", "+user.password);
}

//transpile?
console.log("Hello from main");

users[0].accounts = [
    new Account(500, "checking"),
    new Account(800, 'saving')
]

users[1].accounts = [
    new Account(450, "checking"),
    new Account(850, 'saving')
]

users[2].accounts = [
    new Account(325, "checking"),
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

function getUsername(){
    rl.question("Input your username:",(answer)=>{
        username = answer;
        getPassword();
    })
}

function getPassword(){
    rl.question("Input your password:",(answer)=>{
        password = answer;
        login();
    })
}

function login(){
    loggedUser = users.filter((user: User)=>user.login(username, password))[0];
    if(loggedUser){
        console.log("welcome to the bank");
    }else{
        console.log("invalid credentials, please try again");
        getUsername();
    }
}

function options(){
    rl.question("Select an option:\n1)Deposit\n2)Withdraw\n3)Display Balance",(input) =>{
        switch(input){
        case 1:
            deposit();
        
        case 2:
            withdraw();
        
        case 3:
            display();
     
        }
    })
}

function deposit(){
    let accountType:string;
    rl.question("Please specify which an account to deposit:\n1)Checking\n2)Savings",(answer)=>{
        accountType = answer;
    })
    switch(accountType){
        case "1":
            rl.question("Please enter a deposit amount:", (answer)=> {
                loggedUser.accounts[0].deposit(answer);}
            )
            

        case "2":
        rl.question("Please enter a deposit amount:", (answer)=> {
            loggedUser.accounts[1].deposit(answer);}
        )
    }
}

function withdraw(){
    let accountType:string;
    rl.question("Please specify which an account to withdraw:\n1)Checking\n2)Savings",(answer)=>{
        accountType = answer;
    })
    switch(accountType){
        case "1":
            rl.question("Please enter withdrawal amount:", (answer)=> {
                loggedUser.accounts[0].withdraw(answer);}
            )
            

        case "2":
        rl.question("Please enter a withdrawal amount:", (answer)=> {
            loggedUser.accounts[1].withdraw(answer);}
        )
    }

}

function display(){

    let accountType:string;
    rl.question("Please specify which an account to display balance:\n1)Checking\n2)Savings",(answer)=>{
        accountType = answer;
    })
    switch(accountType){
        case "1":
                console.log(loggedUser.accounts[0].balance);
            
        case "2":
            console.log(loggedUser.accounts[1].balance);
        
    }

}

getUsername();
options();