import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-highlight',
  templateUrl: './highlight.component.html',
  styleUrls: ['./highlight.component.css']
})
export class HighlightComponent implements OnInit {

  constructor() { }

  initialColor; 
  ngOnInit() {
    this.initialColor = document.getElementById("fillertext").style.backgroundColor;
  }

  mouseEnter() {
    document.getElementById("fillertext").style.backgroundColor = "#FFCDD2";
  }

  mouseLeave() {
    document.getElementById("fillertext").style.backgroundColor = this.initialColor;
  }
}
