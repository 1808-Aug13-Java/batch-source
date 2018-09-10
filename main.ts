import {User} from './user';
import {Account} from './account';

let users: User[] = [
    new User('Carl','password36'),
    new User('John','pass1'),
    new User('Bill','h*^352xksh')
]

// for(let user in users){
//     console.log(user);
// }
// console.log("hi?");

users[0].accounts = [
    new Account(500,'checking'),
    new Account(800,'saving')
]
users[1].accounts = [
    new Account(450,'checking'),
    new Account(850,'saving')
]
users[2].accounts = [
    new Account(325,'checking'),
    new Account(250,'saving')
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
    rl.question("Input your username: ",(answer)=>{
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
    loggedUser = users.filter((user:User)=>user.login(username,password))[0];
    if(loggedUser){
        console.log("welcome to the bank " + loggedUser.username);
        menu(loggedUser);
    }else{
        console.log("User credentials not valid. YEET.");
        getUsername();
    }
}

function menu(user:User){
    rl.question("Deposit/Withdraw/View Balance/Log Out\n",(answer)=>{
        if(answer==='Deposit'){
            deposit(user);
        }else if(answer==='Withdraw'){
            withdraw(user);
        }else if(answer==='View Balance'){
            viewBalance(user);
        }else if(answer==='Log Out'){
            logOut();   
        }else{
            console.log("INVALID INPUT. YEET.");
            menu(user);
        }
    })
}

function deposit(user:User){
    rl.question("Amount to deposit to checking: ",(answer:number)=>{
        user.accounts[0].deposit(Number(answer));
        menu(user);
    })
}
function withdraw(user:User){
    rl.question("Amount to withdraw from checking: ",(answer:number)=>{
        user.accounts[0].withdraw(Number(answer));
        menu(user);
    })
}
function viewBalance(user:User){
    console.log("Current checking balance: "+user.accounts[0].balance);
    menu(user);
}

function logOut(){
    console.log("Bye!");
    return process.exit(1);
}
getUsername();