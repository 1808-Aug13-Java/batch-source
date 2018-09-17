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
      firstname: 'Chandrika',
      lastname: 'Sinha',
      email: 'sinhac.rpi@gmail.com',
      birthday: '11/07/1994'
    }, {
      firstname: 'John',
      lastname: 'Doe',
      email: 'johndoe@webmail.com',
      birthday: '1/1/1900'
    }, {
      firstname: 'Mountain',
      lastname: 'Man',
      email: 'MoUnTaInMaN@yahoo.com',
      birthday: '12/31/1604'
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
