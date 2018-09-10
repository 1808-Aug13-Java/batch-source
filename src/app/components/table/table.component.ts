import { Component, OnInit } from '@angular/core';
import { PEOPLE } from '../../mock-people';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  isBootstrap: string = "BootstrapStyle";
  oldStyle: boolean = true;

  people = PEOPLE;

  headers: string[] = ['Full Name', 'Email', 'Birthday'];

  toggleFormatting() {
    this.oldStyle = !this.oldStyle;
    if (this.isBootstrap == "BootstrapStyle") {
      this.isBootstrap = "regular";
    } else {
      this.isBootstrap = "BootstrapStyle";
    }
  }
}
