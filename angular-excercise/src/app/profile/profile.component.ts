import { Component, OnInit } from '@angular/core';
import { User} from '../user';
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user: User = {
    id: 1,
    name: 'Finn'};
  constructor() { }

  ngOnInit() {
  }

}

