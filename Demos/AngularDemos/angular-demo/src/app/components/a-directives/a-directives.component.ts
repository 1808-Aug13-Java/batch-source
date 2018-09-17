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

  colors: string[] = ['blue', 'green', 'yellow', 'red']
  selectedColor: string;
  check: boolean = false;
  disableDropdown() {
    this.check = !this.check;
  }

  classes: string[] = ['italic', 'bold', 'highlight'];
  selectedClasses: string[];

  updateClasses(event) {
    this.selectedClasses = [];
    let opts = event.target.options;
    let current;
    for (let i = 0; i < opts.length; i++) {
      current = opts[i];
      if (current.selected) {
        this.selectedClasses.push(current.text);
      }
    }
  }
}
