import { Component } from "../../../../node_modules/@angular/core";


@Component({
	selector: 'app-databind',
	templateUrl: './databind.component.html',
})
export class DatabindComponent {
	name = "asdf";
	email = "qwer";

	person = {name:'Jim', email:'email@email.com'};
}