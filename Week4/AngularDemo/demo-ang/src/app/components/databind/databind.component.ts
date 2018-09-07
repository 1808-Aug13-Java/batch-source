import {Component} from "@angular/core";

@Component({

    selector:"app-databind",
    templateUrl:"./databind.component.html"

})

export class DatabindComponent{
    person={name:"Jim", email:"jgsmith@gmail.com"};
    name="Jim";
    email="jgsmith@gmail.com";

}