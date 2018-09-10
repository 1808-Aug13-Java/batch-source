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

  user = {
    email: "michaelscott@gmail.com",
    name: "Michael Scott",
    profession: "Paper Salesman",
    age: 67
  };

  notHidden = true;

  hideOrDisplayUser(event) {
    this.notHidden = !this.notHidden;
  }

}
