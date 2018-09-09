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

  str: string = 'hello';
  num: number = 300;
  day: Date = new Date();
  strArr: string[] = ['cat', 'dog', 'rabbit'];
  wordsArr: string[] = ['string-cheese', 'gala-apples', 'ground-beef'];
  

}
