import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile-component',
  templateUrl: './profile-component.component.html',
  styleUrls: ['./profile-component.component.css']
})
export class ProfileComponentComponent implements OnInit {

  user = {name: 'Alex Bumpers', username: 'apbumpers'};

  constructor() { }

  ngOnInit() {
  }

  toggleUser() {
    let myButton = document.getElementById("toggleButton");
    let myProfile = document.getElementById("profile");

    if (myButton.innerText==='Hide') {
      myProfile.style.visibility = 'hidden';
      myButton.innerText='show';
    } else {
      myProfile.style.visibility = 'visible';
      myButton.innerText='hide';
    }
  }

}
