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
  
  user = {name: "Josh", email: "josh@gmail.com"};

  hiding: boolean = false;

  hide(){
    this.hiding = true;
  }
  show(){
    this.hiding = false;
  }
}
