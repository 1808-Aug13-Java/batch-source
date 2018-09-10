import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.css']
})
export class SelectComponent implements OnInit {

  constructor() { }
  animals:string;
  ngOnInit() {
  }
  things = [];
  animals = ["animal1", "animal2", "animal3"];
  colors = ["color1", "color2", "color3"];
  days = ["day1", "day2", "day3"];

  getList(what:string) {
    if(what === "animals") {
      this.things=this.animals;
    }
    else if(what === "days") {
      this.things=this.days;
    }
    else if(what === "colors") {
      this.things=this.colors;
    }
  };
}
