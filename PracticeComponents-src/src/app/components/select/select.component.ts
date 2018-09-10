import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.css']
})
export class SelectComponent implements OnInit {
  checkAnimals : boolean = false;
  checkDays : boolean = false;
  checkColors : boolean = false;
  arrayAnimals : string[] = 
    ["giraffe", "zebra", "lion", "dog", "cat"];
  arrayDays : string[] = 
    ['Monday', 'Tuesday', 'Wednesday', 'Thursday'];
  arrayColors : string[] = 
    ["red", "blue", "orange", 'green'];  

  constructor() { }
  ngOnInit() {
  }

  yesAnimals():void { this.checkAnimals = true; }
  yesColors():void { this.checkColors = true; }
  yesDays():void { this.checkDays = true; }
}
