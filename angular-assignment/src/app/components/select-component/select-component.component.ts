import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-select-component',
  templateUrl: './select-component.component.html',
  styleUrls: ['./select-component.component.css']
})
export class SelectComponentComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  animals = ['cat', 'dog', 'bear'];
  colors = ['red', 'green', 'blue'];
  days = ['sunday','monday','tuesday','wednesday','thursday','friday','saturday'];

  selected: string[];

  showAnimals() {
    this.selected = this.animals;
  }

  showColors() { 
    this.selected = this.colors;
  }

  showDays() {
    this.selected = this.days;
  }

}
