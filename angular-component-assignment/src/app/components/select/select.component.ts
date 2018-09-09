import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.css']
})
export class SelectComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }
  animals: string[] = ["gorilla", "lion", "elephant", "zebra", "tiger", "gazelle"];
  colors: string[] = ["red", "green", "yellow", "blue", "purple", "brown", "black"];
  days: string[] = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
  entries: string[] = [];

  onSelectionChange(event) {
    console.log(event.target.id);
    switch (event.target.id) {
      case "animals":
        this.entries = this.animals;
        break;
      case "colors":
        this.entries = this.colors;
        break;
      case "days":
        this.entries = this.days;
        break;
    }

  }
}
