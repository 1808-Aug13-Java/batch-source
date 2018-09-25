"use strict";
exports.__esModule = true;
var User = /** @class */ (function () {
    function User(user, pass) {
        this.accounts = [];
        this.username = user;
        this.password = pass;
    }
    User.prototype.login = function (user, pass) {
        return this.username === user && this.password === pass;
    };
    User.prototype.getAccount = function (index) {
        return this.accounts[index];
    };
    return User;
}());
exports.User = User;
