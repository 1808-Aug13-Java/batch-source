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

  color: string;

  checked: boolean = false;

  test: string = "style.background-color='red'";


  changeColor(){
    this.checked = !this.checked;

    if(this.checked){

      this.color = 'red';

    }else{
      this.color = 'white';
    }

  }

}
