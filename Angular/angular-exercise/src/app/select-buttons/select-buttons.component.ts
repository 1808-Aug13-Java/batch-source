import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-select-buttons',
  templateUrl: './select-buttons.component.html',
  styleUrls: ['./select-buttons.component.css']
})
export class SelectButtonsComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  animals = ['cow','pig','elephant','t-rex','humans'];
  days = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday'];
  colors = ['red','black', 'blue', 'white', 'cyan'];

  selected: string;
  changeSelected(event) {
    
    this.selected = event.target.value;
    console.log(this.selected);
  }

}
