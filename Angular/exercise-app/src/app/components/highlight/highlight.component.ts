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

  hoverBool: boolean = false;

  hoverOn(){
    this.hoverBool = true;
  }

  hoverOff(){
    this.hoverBool = false;
  }
}
