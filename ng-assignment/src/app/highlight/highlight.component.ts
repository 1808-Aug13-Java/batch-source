import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-highlight',
  templateUrl: './highlight.component.html',
  styleUrls: ['./highlight.component.css']
})
export class HighlightComponent implements OnInit {

  constructor() { }
obj = { "background-color": "red" }
  ngOnInit() {
  }
  count = 0;
   doSomething(i:number) {
     if(i === 1) this.obj = { "background-color": "yellow" }
     if(i === 2) this.obj = { "background-color": "green" }
     if(i === 3) this.obj = { "background-color": "purple" }
     if(i === 4) this.obj = { "background-color": "blue" }
     if(i === 5) this.obj = { "background-color": "grey" }
  }
}
