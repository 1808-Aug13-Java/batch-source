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


  animals = ['dog', 'cat', 'donkey', 'elephant'];
  colors = ['pink', 'orange', 'cyan'];
  days = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday'];

  radio: string[];
  popAnimals() {
    this.radio = this.animals;
  }
  popColors() {
    this.radio = this.colors;
  }
  popDays() {
    this.radio = this.days;
  }

}
