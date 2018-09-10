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

  colors: string[] = ['blue', 'green', 'red', 'yellow'];
  selectedColor: string;

  disableCheck: boolean = true;
  
  classes: string[] = ['italic', 'bold', 'highlight'];
  selectedClasses: string[];

  updateClasses(event){
    this.selectedClasses = [];
    let options = event.target.options;
    let current;

    for(let i = 0; i < options.length; i++){
      current = options[i];
      if (current.selected){
        this.selectedClasses.push(current.value);
      }
    }
  }
}
