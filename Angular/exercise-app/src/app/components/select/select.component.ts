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

  selectedForm: string;

  animalsArr = ["dog", "frog", "log"];
  colorsArr = ["red", "green", "purple"];
  daysArr = ["mon", "tues", "thurs"];

  check(){
    console.log(this.selectedForm);
  }
}
