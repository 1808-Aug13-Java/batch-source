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
 
  animals = ['cat', 'dog', 'spider', 'tardigrade'];
  colors = ['chartreuse', 'indigo', 'magenta', 'black'];
  days = ['Thursday', "birthday", 'Labor Day', 'Saturday'];

  selected: string[];

  displayAnimals(){
    this.selected = this.animals;
  }

  displayColors() {
    this.selected = this.colors;
  }

  displayDays() {
    this.selected = this.days;
  }
}
