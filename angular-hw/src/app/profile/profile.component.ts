import { Component, OnInit } from '@angular/core';
import { User } from './../User';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  profile: User = {
    id: 1,
    name: 'John Doe'
  };

  checked: boolean = false;

  state: string = "hide";

  show(){

    this.checked = !this.checked;

    if(!this.checked){

      this.state="hide";
      
    }else{

      this.state = "show";

    }

  }

  constructor() { }

  ngOnInit() {
  }

}