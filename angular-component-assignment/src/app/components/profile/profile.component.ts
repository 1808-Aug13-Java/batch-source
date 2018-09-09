import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  user = {
    firstName: "Sally",
    lastName: "Jones",
    email: "sjones@example.com",
    address: "123 Main Street",
    phone: "555-555-5555"
  }

  option: string = "hide";
  show: boolean = true;

  toggleProfile() {
    if (this.option == "hide") {
      this.show = false;
      this.option = "show";
    } else {
      this.show = true;
      this.option = "hide";
    }
  }
}
