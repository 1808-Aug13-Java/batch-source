import { Component, OnInit } from '@angular/core';
import { People } from '../../Objects/People';
import { PEOPLES } from '../../Objects/PeopleTable';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {
  arrayPeople : People[] = PEOPLES;
  usingBootstrapFlag : boolean = false;

  constructor() {  }
  ngOnInit() {}
  changeTableFlags(): void {
    //dont forget to use "this."
    this.usingBootstrapFlag = !this.usingBootstrapFlag;
  }
}
