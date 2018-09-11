import { Component, OnInit } from '@angular/core';
import {People} from './../People';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  person1: People = {
    name: 'john doe',
    email: 'jdoe@email.com',
    birthday: new Date('08-19-2010')
  };

  person2: People = {
    name: 'mark smith',
    email: 'msmith@email.com',
    birthday: new Date('02-02-2000')
  };

  person3: People = {
    name: 'nicholas stewart',
    email: 'nstewart@email.com',
    birthday: new Date('05-23-1987')
  };

  checked: boolean = false;

  toggle(){

    this.checked = !this.checked;

  }

  persons: People[] = [this.person1, this.person2, this.person3];

  constructor() { }

  ngOnInit() {
  }

}
