import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-highlight',
  templateUrl: './highlight.component.html',
  styleUrls: ['./highlight.component.css']
})
export class HighlightComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

 example:string =""
  
  change(){
    this.example="example"
  }
  changeBack(){
    this.example=""
  }

  // background($event){
  //   // this.selectedcolor= "blue";
  //   this.color = $event.type == 'mouseover' ? 'blue' : 'white';
  // }
}
