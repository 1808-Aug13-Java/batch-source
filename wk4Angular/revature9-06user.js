"use strict";
exports.__esModule = true;
var User = /** @class */ (function () {
    //3.
    function User(user, pass) {
        this.accounts = []; //2.now lets import Account
        this.username = user;
        this.password = pass;
    }
    //4. make login and getAccount()
    User.prototype.login = function (user, pass) {
        return this.username === user && this.password === pass;
    };
    User.prototype.getAccount = function (index) {
        return this.accounts[index];
        //5. go to main.ts
    };
    return User;
}());
exports.User = User;
