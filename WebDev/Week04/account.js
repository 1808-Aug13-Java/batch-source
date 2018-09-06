"use strict";
// Example of a class. 
// To use outside of this file, use the export keyword. 
exports.__esModule = true;
var Account = /** @class */ (function () {
    function Account(balance, type) {
        this.balance = balance;
        this.type = type;
    }
    // Withdraws money from the account. 
    Account.prototype.withdraw = function (amount) {
        this.balance -= amount;
    };
    Account.prototype.deposit = function (amount) {
        this.balance += amount;
    };
    return Account;
}()); // end of class Accout
exports.Account = Account;
