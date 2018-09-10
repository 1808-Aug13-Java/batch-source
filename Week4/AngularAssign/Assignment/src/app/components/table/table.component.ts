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



  data_table = [
    {
      firstname: 'George',
      lastname: 'Clooney',
      email: 'gc@gmail.com',
      birthday: '01/3/1959'
    }, {
      firstname: 'Amal',
      lastname: 'Clooney',
      email: 'ac@hotmail.com',
      birthday: '3/5/1970'
    }, {
      firstname: 'Bob',
      lastname: 'Builder',
      email: 'bb@aol.com',
      birthday: '4/28/1999'
    }
  ];

  toggle() {
    let tbl = document.getElementById("Populate");
    let tableCls = document.getElementById("this_table");
    console.log(tableCls)
    if (tbl.innerText == "HTML") {
      tableCls.classList.remove("table");
      tbl.innerText = "Bootstrap";
    } else {
      tableCls.classList.add("table");
      tbl.innerText = "HTML";
    }
  }

}
