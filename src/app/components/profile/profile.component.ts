import { Component, OnInit } from '@angular/core';
import { User } from '../../Objects/User';
import { USER } from '../../Objects/Users';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user:User;
  constructor() { this.user = USER; } //better way to get data
      //is using a method from a service :(

  ngOnInit() {
    // this.user.password = "password";
    // this.user.username = "cindy";
  }

}
