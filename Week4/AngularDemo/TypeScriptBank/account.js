"use strict";
exports.__esModule = true;
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
    Account.prototype.checkAccount = function (type) {
    };
    return Account;
}());
exports.Account = Account;
