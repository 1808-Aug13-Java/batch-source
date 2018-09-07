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

  objectKeys = Object.keys;

  userProfile = {
	  name: "Danny",
	  age: 14, 
	  info: "He's a Phantom"
  }
  userProfile2 = [
	"Danny",
	"14",
	"He's a Phantom"
  ]

}
