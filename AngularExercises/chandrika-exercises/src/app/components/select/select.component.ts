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

  animals = ['wombat','platypus','octopus','capybera'];
  colors = ['red','blue','yellow'];
  days = ['sunday','monday','tuesday','wednesday','thursday','friday','saturday'];

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
