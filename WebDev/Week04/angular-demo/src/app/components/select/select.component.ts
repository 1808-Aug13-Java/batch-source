import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.css']
})
export class SelectComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  animalArr = ["Dog", "Wolf", "Cuddlefish", "Velociraptor", "Blob Fish", "Glaucus Atlanticus"]
  colorArr = ["Red", "Green", "Blue", "Yellow", "Gold", "Silver", "Crystal", "Leaf Green", "Fire Red", 
			"Ruby", "Saphire", "Emerald", "Diamond", "Peral", "Platinum", "Black", "White"];
  dayArr = ["Yesterday", "Today", "Tomorrow", "Yesterday's Tomorrow", "Christmas Day", "Birthday"];
}
