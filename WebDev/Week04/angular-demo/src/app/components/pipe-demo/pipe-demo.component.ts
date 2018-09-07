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

  // These are used in the demonstration of pipes. 
  str: string = "Hello World";
  num: number = 5;
  today:Date = new Date();
  strArr: string[] = ['caT', 'Dog', 'RaBiT'];
  wordsArr: string[] = ["Cheese-Taco", "Bandit-Bad", "App-Presents Poorly"];
  wordsLowerArr: string[] = ['This is a Full Sentence that has some lower case words.   ',
							'this is another entierly lower case sentence. BUT NOT THIS ONE!'];
}
