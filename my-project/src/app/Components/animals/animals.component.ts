import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-animals',
  templateUrl: './animals.component.html',
  styleUrls: ['./animals.component.css']
})
export class AnimalsComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  animals: string[] = ["cat", "mouse", "lion", "bear", "sea bass"];
  colors: string[] = ["red", "blue", "green", "orange"];
  days: string[] = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"];

  showAnimals: boolean = false;
  showColors: boolean = false;
  showDays: boolean = false;


  display(show:number){
    switch(show){
      case 1:
      this.showAnimals = true;
      this.showColors = false;
      this.showDays = false;
      break;
      case 2:
      this.showColors = true;
      this.showAnimals = false;
      this.showDays = false;
      break;
      case 3:
      this.showDays = true;
      this.showColors = false;
      this.showAnimals = false;
      break;
    }
  }

}
