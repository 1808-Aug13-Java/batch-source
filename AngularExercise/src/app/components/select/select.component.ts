import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.css']
})
export class SelectComponent implements OnInit {
  animalArray = ['Dog', 'Cat', 'Rabbit', 'Horse', 'Cow', 'Pig', 'Goat', 'Otter'];
  colorArray = ['Green', 'Blue', 'Red', 'Yellow', 'Orange', 'Pink', 'Purple', 'Grey', 'Brown', 'Black', 'White'];
  days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];

  selectedArray: string[];

  radios: NodeListOf<any>;

  constructor() { }

  ngOnInit() {
  }



  updateArray() {
    this.radios = document.getElementsByName('selectAssignment');

    for (const rad in this.radios) {
      if (this.radios[rad].checked) {
        switch (this.radios[rad].value) {
          case 'animals':
          this.selectedArray = this.animalArray;
          break;
          case 'colors':
          this.selectedArray = this.colorArray;
          break;
          case 'days':
          this.selectedArray = this.days;
        }
        break;
      }
    }
  }

}
