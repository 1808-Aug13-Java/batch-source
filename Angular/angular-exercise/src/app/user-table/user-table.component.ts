import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-user-table",
  templateUrl: "./user-table.component.html",
  styleUrls: ["./user-table.component.css"]
})
export class UserTableComponent implements OnInit {
  constructor() {}

  ngOnInit() {}

  users = [
    {
      firstName: "bob",
      lastName: "fischer",
      email: "chessgod@gmail.com",
      birthday: new Date("1978-03-21")
    },
    {
      firstName: "tom",
      lastName: "mauler",
      email: "cheesecake@gmail.com",
      birthday: new Date("1997-04-21")
    },
    {
      firstName: "ellenore",
      lastName: "rosee",
      email: "fotus@gmail.com",
      birthday: new Date("1954-09-09")
    }
  ];

  doDisplay: boolean = false;
  

  toggleClasses(event) {
    this.doDisplay = !this.doDisplay;
    if(this.doDisplay) {
      event.target.innerHTML = "Turn Off Bootstrap";
    } else {
      event.target.innerHTML = "Turn On Bootstrap";
    }
  }
}
