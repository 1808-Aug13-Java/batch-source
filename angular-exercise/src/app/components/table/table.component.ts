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
    firstname: 'Charles',
    lastname: 'Crandall',
    email: '11ccrandall@gmail.com',
    birthday: '2/10/1993'
  },{
      firstname: 'Chandrika',
      lastname: 'Sinha',
      email: 'sinhac.rpi@gmail.com',
      birthday: '11/07/1994'
  },{     
      firstname: 'Maxwell',
      lastname: 'Mooney',
      email: 'maxeffect13@yahoo.com',
      birthday: '12/31/1404'
  }
];
  
  toggle() {
    let element = document.getElementById("htmlorbootstraptable");
    let info = document.getElementById("persontable");
    if (element.innerText=="See HTML table") {
      info.classList.remove("table");
      element.innerText="See Bootstrap table";
    } else {
      info.classList.add("table");
      element.innerText="See HTML table";
    }
  }
}
