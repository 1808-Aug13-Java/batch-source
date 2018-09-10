import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user = {name: 'Thor', email: 'thor_odinson@outlook.com', age: 1500, occupation: 'King of Asgard'};

  constructor() { }

  ngOnInit() {
  }

  toggleTable() {
    const table = document.getElementById('userTable');
    const b = document.getElementById('toggleButton');
    if (table.hidden === true) {
      table.hidden = false;
      b.innerHTML = 'Hide';
    } else {
       table.hidden = true;
      b.innerHTML = 'Show';
    }
  }

}
