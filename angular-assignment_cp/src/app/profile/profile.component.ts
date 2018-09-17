import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor() { }

  private isButtonVisible = false;
  private hiddenButtonVisible = true;
  profile = { 
    username : "ReadingRainbow", 
    name : "LeVar Burton",
    phone : "918-555-2323"
     };

  ngOnInit() {
  
  }

}
