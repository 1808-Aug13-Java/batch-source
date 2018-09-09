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
  color: string = "blue";

  changeBackgroundColor($event){
    this.color = $event.type == 'mouseover' ? 'blue' : 'white';
  }
}
