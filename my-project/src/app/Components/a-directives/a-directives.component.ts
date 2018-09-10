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

  colors: string[] = ['blue','green','red','yellow'];

  canSelect: boolean = true;

  classes: string[] = ['italic','bold','highlight'];
  selectedClasses: string[];

  changeSelect()
  {
    this.canSelect = !this.canSelect;
  }
  
  updateClasses(event){
    this.selectedClasses = [];
    let opts = event.target.options;
    let current;

    for(let i=0; i<opts.length;i++){
      current = opts[i];
      if(current.selected){
        this.selectedClasses.push(current.text);
      }
    }
  }
}
