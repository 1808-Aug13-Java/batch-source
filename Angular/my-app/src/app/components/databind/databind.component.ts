import { Component } from "@angular/core";

@Component({
    templateUrl: './databind.component.html',
    selector: 'app-databind'
})
export class DatabindComponent{
    person = {name:'Jim', email:'jim@gmail.com'};
    name = 'Jim';
    email = 'jim@gmail.com';
}