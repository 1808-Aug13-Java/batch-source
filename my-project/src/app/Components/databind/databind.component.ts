import { Component } from "../../../../node_modules/@angular/core";

@Component({
    selector: 'app-databind',
    templateUrl: './databind.component.html'
})

export class DatabindComponent{

    name = "Jim";

    email = "jgsmith@gmail.com";

    person = {name:"Jim", email:"jgsmith@gmail.com"};
}