import { Component } from "@angular/core";


@Component({
    selector: 'app-databind',
    templateUrl: './databind.component.html'
})
export class DatabindComponent{

    name = 'Jimmy';

    email = 'jgsmith@gmail.com';

    person = {name:'Jimmy', email:'jgsmith@gmail.com'};

}