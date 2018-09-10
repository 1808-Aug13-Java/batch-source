import { Component, OnInit } from '@angular/core';
import { Person } from '../person';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }
  users: Person[]=[
    {
    first: 'billybob',
    last: 'thornton',
    email: 'bthorn@gmail.com',
    birthday: new Date(1955, 7, 4)
    },{
    first: 'JocelYne',
    last: 'ribcage',
    email: 'JRCage@gmail.com',
    birthday: new Date(1950, 0, 13)
    },{
    first: 'sammilyn',
    last: 'Aislok',
    email: 'samais@gmail.com',
    birthday: new Date(2000, 0, 1)
    }
  ]
  boot:boolean = true;
  style(){
    this.boot = !this.boot;
  }
}
