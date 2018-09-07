import { Component } from "@angular/core";

@Component({
  selector: 'app-databind',
  templateUrl: './databind.component.html'

})

export class DataBindComponent {
  name = "jim";
  email = "jgsmith@gmail.com"
  person = {name: "Jim", email:"jgsmit@gmail.com"};
}