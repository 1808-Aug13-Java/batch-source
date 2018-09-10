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

  Name: string = "Cirey Francis";
  Age: number = 23;
  Occupation: string = "Full Stack Developer";
  Gender: string = "Male";
  isVisible: boolean = false;

  display(){
    this.isVisible = !this.isVisible;
  }
  

}
