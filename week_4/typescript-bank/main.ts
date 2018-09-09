import {User} from './user';
import {Account} from './account';

let users: User[] = [
    new User('Carl', 'password36'),
    new User('John', 'pass1'),
    new User('Bill', 'h*^352xksh')
]

// for (let user in users) {
//     console.log(user.username+ ", " + user.password);
// }

users[0].accounts = [
    new Account(500, "checking"),
    new Account(800, 'saving')
]

users[1].accounts = [
    new Account(325, "checking"),
    new Account(250, 'saving')
]

users[2].accounts = [
    new Account(450, "checking"),
    new Account(850, 'saving')
]

const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
})

console.log("Hello from main");

let username: string;
let password: string;
let loggedUser: User;
let checking: string;

getUsername();

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
        getCheckingOrSaving();
    } else {
        console.log("Invalid credentials, please try again");
        getUsername();
    }
}

function getCheckingOrSaving() {
    rl.question("Checking or savings?", (answer) => {
        checking = answer;

        if (checking === "Checking" || checking === "checking") {
            // loggeduser = users[0].accounts((account: Account) => getCheckingOrSaving(checking))
            console.log("Enter amount to deposit into CHECKING account: ");
        } else if (checking === "Saving" || checking === "saving") {
            console.log("Enter amount to deposit into SAVINGS account: ");
        } else {
            console.log("invalid account type.")
        }
        console.log("working")
    })
}