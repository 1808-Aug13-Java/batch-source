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

  count: number = 0;
  isMultipleOfFive = this.count % 5 === 0 && this.count != 0;
  increment(count: number) {
    this.count += count;
    this.isMultipleOfFive = this.count % 5 === 0;
  }

}
