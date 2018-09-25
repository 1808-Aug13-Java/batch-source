import { Component, OnInit } from '@angular/core';
import { fromEventPattern } from 'rxjs';

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
  check:boolean=true;
  changeChecked(){
    this.check=!this.check;
  }

  classes:string[]= ['italic','bold','highlight'];
  selectedClass :string[]

  updateClasses(event){
    this.selectedClass=[];
    let options=event.target.options;
    let current;

    for(let i =0; i<options.length;i++){
      current=options[i];
      if(current.selected){
        this.selectedClass.push(current.text)
      }
      
    }
  }


}