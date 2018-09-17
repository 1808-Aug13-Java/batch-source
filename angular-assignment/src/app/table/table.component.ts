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

  person = [
    {
      fname: 'julie',
      lname: 'seals',
      email: 'email@email.com',
      birthday: '4/23/1984'
    },{
      fname: 'jessie',
      lname: 'seals',
      email: 'Sister@email.com',
      birthday: '5/6/1977'
    },{
      fname : 'jonnie',
      lname: 'seals',
      email : 'Brother2@email.com',
      birthday : '1/11/1991'
    },{
      fname : 'justin',
      lname: 'seals',
      email : 'Brother1@email.com',
      birthday : '6/7/1981'
    }
  ];

  private htmlButton = false;
  private bsButton = true;
  
}
