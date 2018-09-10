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

  animals: string[] = ["Kiwi(best animal)","Sloth","Tiger","Panther","Red Panda"];
  colors: string[] = ["black", "green", "grey", "purple", "red"];
  days: string[] = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday","Sunday"];
  selected: string[] = [];

  onSelectionChange(event) {
    console.log(event.target.id);
    
    switch (event.target.id) {
      case "animals":
        this.selected = this.animals;
        break;
      case "colors":
        this.selected = this.colors;
        break;
      case "days":
        this.selected = this.days;
        break;
    }
  }
}
