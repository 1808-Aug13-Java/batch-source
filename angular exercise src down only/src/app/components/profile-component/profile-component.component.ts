import { Component, OnInit } from '@angular/core';
import { User1 } from '../../user1';

@Component({
  selector: 'app-profile-component',
  templateUrl: './profile-component.component.html',
  styleUrls: ['./profile-component.component.css']
})

/*
2. Profile Component
Create a component that displays user information - populated from a user object in its class
Create a button which toggles the visibility of this information. Button text should also toggle between saying “hide” and “show”
*/

export class ProfileComponentComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  isClicked: boolean = false;

  user: User1 = {
    age: 21,
    name: 'Baron'
  }

  hideOrNah: string = 'unhide';

  changeClicked(){
    this.isClicked = !this.isClicked;
    this.toggleHide();
  }

  toggleHide(){
    if(this.hideOrNah === 'unhide'){
      this.hideOrNah = 'hide';
    } else {
      this.hideOrNah = 'unhide';
    }
  }

}
