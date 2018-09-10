import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-highlighter-component',
  templateUrl: './highlighter-component.component.html',
  styleUrls: ['./highlighter-component.component.css']
})
export class HighlighterComponentComponent implements OnInit {

  constructor() { }

  colorStart;

  ngOnInit() {
    this.colorStart = document.getElementById("lorumipsum").style.backgroundColor;
  }

  colorize() {
    document.getElementById("lorumipsum").style.backgroundColor = "#4286f4";
  }

  decolorize() {
    document.getElementById("lorumipsum").style.backgroundColor = this.colorStart;
  }

}
