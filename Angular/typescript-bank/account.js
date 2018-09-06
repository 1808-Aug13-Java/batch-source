"use strict";
exports.__esModule = true;
// Need to include "export" keyword to use it in other files
var Account = /** @class */ (function () {
    function Account(b, t) {
        this.balance = b;
        this.type = t;
    }
    Account.prototype.withdraw = function (amount) {
        this.balance -= amount;
    };
    Account.prototype.deposit = function (amount) {
        this.balance += amount;
    };
    Account.prototype.currBalance = function () {
        return this.balance;
    };
    return Account;
}());
exports.Account = Account;
