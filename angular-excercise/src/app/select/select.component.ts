import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.css']
})
export class SelectComponent implements OnInit {
  entries = [];
  selectedEntry: { [key: string]: any } = {
    value: null,
    description: null
  };
  constructor() { }

  ngOnInit() {
    this.entries = [
      {
        description: 'animals',
        id: 1
      },
      {
        description: 'colors',
        id: 2
      },
      {
        description: 'days',
        id: 3
      }];
    // onSelectionChange(entry) {
    //   // clone the object for immutability
    //   this.selectedEntry = Object.assign({}, this.selectedEntry, entry);
    // }
    // if(this.entries) {
    //   this.onSelectionChange(this.entries[0]);  
    // }
    
  }

}
