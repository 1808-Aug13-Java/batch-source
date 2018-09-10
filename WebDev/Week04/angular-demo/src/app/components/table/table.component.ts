import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  

  people: Person[] = [
	{first_name:"hector", last_name:"bolovski", email:"hector123@hotmail.net", birthday:new Date()},
	{first_name:"Vinny", last_name: "Lange", email:"vinesauce@gmail.com", birthday:new Date("Jan 1, 1990")},
	{first_name:"Gertrude", last_name: "Baker", email:"bakerst@gamil.com", birthday:new Date("Jan 2, 1990")},
  ];

  divClass: string = "container";
  tableClass: string = "table";
  tableHeadClass: string = "thead-dark";

  // Toggles the bootstrap elements by filling or unfilling them. 
  toggleBootstrap() {
	// If there is no class defined, set the bootstrap classes. 
	if (this.divClass.length == 0) {
		this.divClass = "container";
		this.tableClass = "table";
		this.tableHeadClass = "thead-dark";
	}
	// Otherwise, clear the bootstrap.
	else {
		this.divClass = "";
		this.tableClass = "";
		this.tableHeadClass = "";
	}
  }


}
