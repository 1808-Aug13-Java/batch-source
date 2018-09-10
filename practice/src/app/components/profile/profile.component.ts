import { Component, OnInit } from '@angular/core';
import { User } from '../../../user';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }
  bob:User={
    id:1,
    name:'Bob',
    email:'bob@gmail.com'
  };
  
}

