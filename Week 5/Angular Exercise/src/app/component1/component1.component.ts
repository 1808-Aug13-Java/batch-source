import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-component1',
  templateUrl: './component1.component.html',
  styleUrls: ['./component1.component.css']
})
export class Component1Component implements OnInit {

  constructor() { }

  check:boolean = false;

  garbage: String = 'Create a component which has some filler text - the background of the text should change to the color of your choice when you hover over the text';
  hoverin(): void {
    this.check = true;
  }
  hoverout(): void {
    this.check = false;
  }
  courses = [{
    id: 6, 
    name: 'history'
  },{
    id: 42, 
    name: 'art'
  },{
    id: 2, 
    name: 'math'
  },]

  ngOnInit() {
  }


}
