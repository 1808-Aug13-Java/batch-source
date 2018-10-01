import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pipe-demo',
  templateUrl: './pipe-demo.component.html',
  styleUrls: ['./pipe-demo.component.css']
})
export class PipeDemoComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  str: string = 'heLlo';
  num: number = 5;
  day: Date = new Date();
  strArr: string[] = ['caT', 'dOg', 'rAbBIt'];
  wordsArr: string[] = ['string-cheese','gala-apples','ground-beef'];

}
