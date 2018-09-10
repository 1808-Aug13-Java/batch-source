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
    show = false;
    text = "show";
  user = {
    firstname: "jerry",
    lastname: "kim", 
  };
  toggle() {
    this.show = !this.show;
    if(this.show) this.text = "show";
    else this.text = "hide";
  }
}
