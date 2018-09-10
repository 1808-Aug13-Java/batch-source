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
  animals = ['cat','dog','octopus','dolphin'];
  colors = ['red','blue','green'];
  days = ['Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday'];

  selected: string[];
  setAnimals() {
    this.selected = this.animals;
  }
  setColors() {
    this.selected = this.colors;
  }
  setDays() {
    this.selected = this.days;
  }
}
