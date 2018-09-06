"use strict";
exports.__esModule = true;
// Another class example
var User = /** @class */ (function () {
    function User(user, pass) {
        this.accounts = [];
        this.username = user;
        this.password = pass;
    }
    // Validate login
    User.prototype.login = function (user, pass) {
        // console.log(this.username + "==" + user + " && " + this.password + "==" + pass);
        // console.log((this.username == user) + " && " + (this.password == pass));
        return this.username == user && this.password == pass;
    };
    User.prototype.getAccount = function (index) {
        return this.accounts[index];
    };
    return User;
}()); // end of class User
exports.User = User;
