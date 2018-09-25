import {User} from "./user";
import {Account} from "./account";


let users:User[]=[

    new User('Carl','password16'),
    new User('John', 'pass1'),
    new User('Bill', 'dope')
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

// for(let user in users){
//     console.log(user.username+" "+ user.password);
// }


const readline= require('readline');

const rl=readline.createInterface({
    input:process.stdin,
    output:process.stdout

})


let username:string;
let password:string;
let loggedUser:User;
let workingAccount:Account;

getUsername();

function getUsername(){
    rl.question("Input your question",(answer)=>{
        username=answer;
        getPassword();
    })
}

function getPassword(){
    rl.question("input your password",(answer)=>{
        password=answer;
        login();
    })
}

function login(){
    loggedUser=users.filter((user: User)=>user.login(username,password))[0] ;
    if(loggedUser){
        console.log("welcome to the bank");
        menu();
    }
    else{
        console.log("invalid credentials, please try again");
        getUsername();
    }
}


function menu(){
// console.log("Please select the number for the transaction you would like to perform.")
// console.log("1.withdraw from account");
// console.log("2.Deposit to an account");
    rl.question("Choose an account, 1.Checking or 2.Savings ", (answer) => {
        let choice = answer;
        switch (choice) {
            case 1: {
                //withdraw

                // workingAccount = Account.filter()
                workingAccount=loggedUser.accounts[0];
                whichTrans();
            }
            case 2: {
                //deposit
                workingAccount = loggedUser.accounts[1];
                whichTrans();
            }
            default: {
                console.log("Please select a valid choice")
                menu();
            }
        }
    })
}

function whichTrans(){
    rl.question("Choose a transaction, 1.withdraw or 2.deposit", (answer) =>{
        let choice =answer;
        switch(choice){
            case 1:{workingAccount.withdraw(answer);
                menu();}
            case 2:{workingAccount.deposit(answer)
                menu();}
            default:{
                console.log("Please select a valid option");
                whichTrans();
            }


        }})
}



