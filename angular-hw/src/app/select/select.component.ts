import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.css']
})
export class SelectComponent implements OnInit {

  result : string;

  animals : string[] = ["Giraffe", "Lion", "Tiger", "Horse", "Zebra"];
  colors : string[] = ["Red", "Blue", "Yellow", "Purple", "Green"];
  days : string[] = ["Monday", "Tuesday", "Wednesday","Thursday", "Friday"];

  constructor() { }

  ngOnInit() {
  }

}
