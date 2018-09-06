import {User} from './user';
import {Account} from './account';


let users: User[] = [
    new User('Carl', 'password36'),
    new User('John', 'pass1'),
    new User('Bill', 'h*^352xksh')
]

for(let user in users){
    console.log(user);
}




users[0].accounts = [
    new Account(500, 'checking'),
    new Account(800, 'saving')
]
users[1].accounts = [
    new Account(500, 'checking'),
    new Account(800, 'saving')
]
users[2].accounts = [
    new Account(500, 'checking'),
    new Account(800, 'saving')
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
    rl.question("Input your username: ", (answer)=> {
        username = answer;
        getPassword();
    })
}

function getPassword(){
    rl.question("Input your password: ", (answer)=>{
        password = answer;
        login();
    })
}

let deposit: string;
let withdraw: string;

function login(){
    loggedUser = users. filter((user: User)=>user.login(username,password))[0];
    if(loggedUser){
        console.log("welcome to the bank");
        
        
    }else{
        console.log("invalid credentials, please try again");
        getUsername();
    }
}


function withdrawOrDeposit(){
    loggedUser = account. filter((account: Account)=>account.withdrawOrDeposit(deposit,withdraw))[0];
    if(loggedUser){
        rl.question("Please select: 'Deposit' or 'Withdraw': ", (answer)=>{
            deposit = answer;
        })
        
    }else{
        console.log("invalid amount, please try again");
        
    }
}
