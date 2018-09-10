import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-highlight',
  templateUrl: './highlight.component.html',
  styleUrls: ['./highlight.component.css']
})
export class HighlightComponent implements OnInit {

  constructor() { }

  changeColor(ch: boolean) {
    const para = document.getElementById('colorChanger');
    if (ch) {
      para.style.backgroundColor = 'rgb(0,196,255)';
    } else {
      para.style.backgroundColor = 'rgb(255,255,255)';
    }
  }

  ngOnInit() {
  }

}
