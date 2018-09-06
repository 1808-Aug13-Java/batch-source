"use strict";
exports.__esModule = true;
//use the export keyword to use this anywhere else
var Account = /** @class */ (function () {
    function Account(balance, type) {
        this.balance = balance;
        this.type = type;
    }
    Account.prototype.withdraw = function (amount) {
        this.balance -= amount;
    };
    Account.prototype.deposit = function (amount) {
        this.balance += amount;
    };
    return Account;
}());
exports.Account = Account;
