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

    animalsArr = ["rabbit" , "cat", "dog"];
    colorsArr = ["white", "orange", "brown"];
    daysArr = ["Monday", "Tuesday", "Wednesday"];

    list: string[];

    showAnimals(){
      this.list = this.animalsArr;
    }

    showColors(){
      this.list = this.colorsArr;
    }

    showDays(){
      this.list = this.daysArr;
    }
}
