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

  people: Person[] = [{
    firstname: "josh",
    lastname: "park",
    email: "JoshP@gmail.com",
    birthday: new Date(2000, 1, 10)
  }, {
    firstname: "kimberly",
    lastname: "whitman",
    email: "KimW@gmail.com",
    birthday: new Date(1983, 4, 20)
  }, {
    firstname: "albert",
    lastname: "song",
    email: "AlbertS@gmail.com",
    birthday: new Date(1800, 8, 10)
  }]

  table: string;

  changeClass(){
    if(this.table != "table"){
      this.table = "table";
    } else {
      this.table = " ";
    }
  }
}
