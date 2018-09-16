import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.css']
})

/*
3. Select Component
Create a component with three radio buttons: one labeled “animals,” the next labeled “colors,” and the last labeled “days”  
When selected, a list (generated from arrays in the class) will be displayed containing a few animals, colors, or days respectively
*/

export class SelectComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  animals: string[] = ['cat', 'dog', 'turtle', 'elephant'];
  animalChecked: boolean = false;
  colors: string[] = ['blue', 'green', 'red', 'yellow'];
  colorChecked: boolean = false;
  days: string[] = ['monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday', 'sunday'];
  daysChecked: boolean = false;

  //probably shouldn't have three different methods for this,
  //will change later...

  changeAnimalChecked(){
    this.animalChecked = !this.animalChecked;
  }

  changeColorChecked(){
    this.colorChecked = !this.colorChecked;
  }

  changeDayChecked(){
    this.daysChecked = !this.daysChecked;
  }

}
