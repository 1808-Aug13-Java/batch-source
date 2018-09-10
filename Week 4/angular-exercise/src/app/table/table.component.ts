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

  people = [
    {
      firstname: 'John',
      lastname: 'Smith',
      email: 'jsmith@gmail.com',
      birthday: '1/01/1991'
    }, {
      firstname: 'Jane',
      lastname: 'Doe',
      email: 'jdoe@gmail.com',
      birthday: '2/02/1992'
    }, {
      firstname: 'Henry',
      lastname: 'Saing',
      email: 'hsaing@yahoo.com',
      birthday: '07/29/1994'
    }
  ];
}
