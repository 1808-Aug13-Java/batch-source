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
  str: string = "db";
  num: number = 5;
  day: Date = new Date();
  strArr: string[] = ['cAT', 'DOg', 'rAbBiT'];
  wordsArr: string[] = ['string-cheese', 'gala-aples', 'ki-wo'];
}
