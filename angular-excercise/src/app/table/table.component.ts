import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  peopleArr = [
    {
      name: 'Earl of Lemongrab',
      email: 'Unknown',
      birthday: 'April 2, 1989'
    },
    {
      name: 'Bonnibel Bubblegum',
      email: 'bb@cn.com',
      birthday: 'May 1, 2020'
    },
    {
      name: 'Phoebe',
      email: 'phoebs@cn.com',
      birthday: 'June 10, 1999'
    },
    {
      name: 'Lumpy Space Princess',
      email: 'lsp@cn.com',
      birthday: 'November 14, 1922'
      
    },
  ]

  constructor() { }

  ngOnInit() {
  }

}
