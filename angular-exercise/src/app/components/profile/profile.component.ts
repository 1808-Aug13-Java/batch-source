import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  shown:boolean = true;
  constructor() { }

  user = {name:"Charles", email:"11ccrandall@gmail.com"};
  ngOnInit() {
  }

  switchState(m){
    document.getElementById('btn').innerText = this.shown?"hide":"show";
  }
}
