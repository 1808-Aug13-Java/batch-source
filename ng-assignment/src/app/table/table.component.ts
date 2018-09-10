import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {
  constructor() { 
  }
  people = [
    { firstname: "firstname1", lastname: "lastname1", email: "email1@email.com", birthdate: "2002-02-03" }, 
    { firstname: "firstname2", lastname: "lastname2", email: "email2@email.com", birthdate: "2003-03-04" }, 
    { firstname: "firstname3", lastname: "lastname3", email: "email3@email.com", birthdate: "2004-04-05" }, 
    { firstname: "firstname4", lastname: "lastname4", email: "email4@email.com", birthdate: "2005-06-06" }, 
  ];
  ngOnInit() {
  }

  tableClass = "";
  toggleTableClass() {
    if(this.tableClass === "table table-dark") {
      this.tableClass = "";
    }
    else {
      this.tableClass = "table table-dark";
    }

  }
}
