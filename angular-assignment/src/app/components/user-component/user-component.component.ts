import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-user-component',
  templateUrl: './user-component.component.html',
  styleUrls: ['./user-component.component.css']
})
export class UserComponentComponent implements OnInit {

  constructor(private userService: UserService) { }

  ngOnInit() {
  }

  users: User[];
  getUsers() {
    this.userService.getUsers()
    .subscribe((allUsers) => this.users = allUsers);
  }

}
