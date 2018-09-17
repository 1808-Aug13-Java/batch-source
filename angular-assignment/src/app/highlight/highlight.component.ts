import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-highlight',
  templateUrl: './highlight.component.html',
  styleUrls: ['./highlight.component.css']
})
export class HighlightComponent implements OnInit {

  constructor() {}
  defaultColor;

  ngOnInit(){
    this.defaultColor = document.getElementById("filler").style.backgroundColor;
  }

  mouseenter() {
    document.getElementById("filler").style.backgroundColor = "aqua";
  }

  mouseleave() {
    document.getElementById("filler").style.backgroundColor = this.defaultColor;
  }

}
