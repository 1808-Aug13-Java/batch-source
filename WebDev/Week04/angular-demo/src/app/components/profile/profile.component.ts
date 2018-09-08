import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  // Saves a function that returns an array of the keys. This is required as 
  // to parse through the userProfile map. 
  objectKeys = Object.keys;

  userProfile = {
	  name: "Danny",
	  age: 14, 
	  info: "He's a Phantom"
  }
  
  isVisible: boolean = false;
}
