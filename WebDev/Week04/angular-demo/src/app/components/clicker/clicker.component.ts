import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-clicker',
  templateUrl: './clicker.component.html',
  styleUrls: ['./clicker.component.css']
})
export class ClickerComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  // Keeps track of a count of times a button was pressed
  count = 0;

  increment(inc: number) {
	  this.count += inc;
  }

}
