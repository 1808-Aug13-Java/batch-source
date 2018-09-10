import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-highlight',
  templateUrl: './highlight.component.html',
  styleUrls: ['./highlight.component.css']
})
export class HighlightComponent implements OnInit {

  constructor() { }
  default: string;
  ngOnInit() {
    this.default = document.getElementById('fillertext').style.backgroundColor;
  }

  mouseEnter(self) {
    self.path[0].style.backgroundColor = "#b3f713";
  }

  mouseLeave(self) {
    self.path[0].style.backgroundColor = this.default;
  }
}