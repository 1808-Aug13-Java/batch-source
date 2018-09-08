import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.css']
})
export class SelectComponent implements OnInit {
  constructor() { }
Something(){
  return 0;
}
  animalArr = ["dog", "cat", "duck"];
  colorArr = ["black", "blue", "death"];
  dayArr = ["Fat-Tuesday", "Wide-Wednesday"];
  ngOnInit() {
    
  }
 
}
