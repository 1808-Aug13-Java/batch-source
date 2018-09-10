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

 isHover: boolean = false;
 highlightClass: string;

 changeHighlight()
 {
   this.isHover = !this.isHover;
   if(this.isHover === true)
   {
     this.highlightClass = 'red';
   }
   else{
     this.highlightClass = '';
   }
 }

}
