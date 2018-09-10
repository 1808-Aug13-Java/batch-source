import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})

export class TableComponent implements OnInit {
  data = [
    {first: 'John', last: 'Jacko', email: 'jljacko@outlook.com', birthdate: new Date(1993, 8, 23)},
    {first: 'M', last: 'C', email: 'mtc@outlook.com', birthdate: new Date(1993, 4, 14)},
    {first: 'Lord', last: 'Trooper', email: 'ltrooper@outlook.com', birthdate: new Date(2003, 6, 15)}
  ];
  tableClass = '';
  buttonClass = '';
  doBootstrap = false;
  constructor() { }

  ngOnInit() {
  }



  toggleBoot() {
    this.doBootstrap = !this.doBootstrap;
    if (this.doBootstrap) {
      this.tableClass = 'table';
      this.buttonClass = 'btn btn-info';
    } else {
      this.tableClass = '';
      this.buttonClass = '';
    }
  }


}
