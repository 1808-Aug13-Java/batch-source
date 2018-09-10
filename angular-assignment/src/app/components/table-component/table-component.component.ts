import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-table-component',
  templateUrl: './table-component.component.html',
  styleUrls: ['./table-component.component.css']
})
export class TableComponentComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  person = [
    {
      firstname: 'Alex',
      lastname: 'Bumpers',
      email: 'ab@gmail.com',
      birthday: '11/10/1993'
    }, {
      firstname: 'John',
      lastname: 'Smith',
      email: 'jsmith@aol.com',
      birthday: '1/1/1968'
    }, {
      firstname: 'George',
      lastname: 'Washington',
      email: 'washington@america.com',
      birthday: '02/22/1732'
    }
  ];

  togglePeople() {
    let tableSetter = document.getElementById("setPeople");
    let peopleSetter = document.getElementById("peopletable");

    if (tableSetter.innerText=="View html table") {
      peopleSetter.classList.remove("table");
      tableSetter.innerText="View bootstrap table";
    } else {
     peopleSetter.classList.add("table");
     tableSetter.innerText="View html table";
    }

  }

}
