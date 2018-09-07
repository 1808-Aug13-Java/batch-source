import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-s-directives',
  templateUrl: './s-directives.component.html',
  styleUrls: ['./s-directives.component.css']
})
export class SDirectivesComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  // My stuff below here

  // Used to demonstrate how to use templates and *ngIf
  condition: boolean = true;

  changeCondition(): void {
	this.condition = !this.condition;
  }

  // Used to demonstrate how to access data and how to use an *ngFor loop. 
courses = [
{
	id: 6,
	name: 'history'
}, {
	id: 7,
	name: 'math'
}, {
	id: 8,
	name: 'science'
},
];

	// Used to demonstrate a switch case
	time: string = "sometime";

}
