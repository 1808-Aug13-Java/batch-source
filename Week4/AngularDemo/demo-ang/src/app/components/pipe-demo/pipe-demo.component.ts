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

  str:string='hello, is it me your looking for?'
  num:number =5;
  day: Date=new Date();
  strArr:string[]=['caT','dOg','rAbbit'];
  wordsArr:string[]=['gala-applies', 'yeah-son','nope','this-that'];
  word:string;
  ex:string='100';

}
