import { User } from './user';
import { Account } from './account';

let users: User[] = [
    new User('Carl', 'password36'),
    new User('John', 'pass1'),
    new User('Bill', 'h*^352xksh')
]

// for (let user of users) {
//     console.log(user.username + " " + user.password);
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

function getUsername() {
    rl.question("Input your username:", (answer) => {
        username = answer;
        getPassword();
    })
}

function getPassword() {
    rl.question("Input your password:", (answer) => {
        password = answer;
        login();
    })
}

function login() {
    loggedUser = users.filter((user: User) => user.login(username, password))[0];
    if (loggedUser) {
        console.log("Welcome to the bank");
        displayBalances();

    } else {
        console.log("invalid credentials, please try again");
        getUsername();
    }
}

function displayBalances() {
    for (let i = 0; i < loggedUser.accounts.length; i++) {
        console.log(i + ": " + loggedUser.accounts[i].type + " " + parseFloat(loggedUser.accounts[i].balance).toFixed(2));
    }
    getTransactionType();
}

function getTransactionType() {
    let transaction: string;
    rl.question("Enter 1 to make a deposit, 2 to make a withdrawal, or 3 to exit:", (answer) => {
        transaction = answer;
        switch (transaction) {
            case "1":
                deposit();
                break;
            case "2":
                withdraw();
                break;
            case "3":
                console.log("Thank you for your business. Please come again.");
                process.exit(0);
            default:
                console.log("Invalid option.");
                displayBalances();
        }
    })
}

function withdraw() {
    let index: number;
    rl.question("Enter account index:", (answer) => {
        index = parseInt(answer);
        if (index >= 0 && index < loggedUser.accounts.length) {
            let amount: number;
            rl.question("Enter transaction amount:", (answer) => {
                amount = parseFloat(answer);
                if (amount >= 0.01) {
                    if (loggedUser.accounts[index].balance >= amount) {
                        loggedUser.accounts[index].withdraw(amount);
                        displayBalances();
                    } else {
                        console.log("Cannot withdraw more than your balance.");
                        displayBalances();
                    }
                } else {
                    console.log("Invalid or negative amount.");
                    displayBalances();
                }
            });
        } else {
            console.log("Invalid index. Please try again.");
            displayBalances();
        }
    });
}

function deposit() {
    let index: number;
    rl.question("Enter account index:", (answer) => {
        index = parseInt(answer);
        if (index >= 0 && index < loggedUser.accounts.length) {
            let amount: number;
            rl.question("Enter transaction amount:", (answer) => {
                amount = parseFloat(answer);
                if (amount >= 0.01) {
                    loggedUser.accounts[index].deposit(amount);
                    displayBalances();
                } else {
                    console.log("Invalid or negative amount.");
                    displayBalances();
                }
            });
        } else {
            console.log("Invalid index. Please try again.");
            displayBalances();
        }
    });

}

getUsername();