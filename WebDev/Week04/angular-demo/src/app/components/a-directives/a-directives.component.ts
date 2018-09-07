import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-a-directives',
  templateUrl: './a-directives.component.html',
  styleUrls: ['./a-directives.component.css']
})
export class ADirectivesComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }



  colors: string[] = ["red", "blue", "yellow"];

  // Used to enable or disable the checkbox. Not necessary to declare here though. 
  enabledCheck: boolean;

  // An array of strings
  selectedClasses: string[];

  classes: string[] = ['italic', 'bold', 'highlight'];


  updateClasses(event) {
	this.selectedClasses = [];

	let options = event.target.options;
	let opt;

	for (let i=0; i<options.length; i++) {
		opt = options[i];
		if (opt.selected) {
			this.selectedClasses.push(opt.text);
		}
	}
  }

}
