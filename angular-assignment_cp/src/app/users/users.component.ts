import { Component, OnInit } from '@angular/core';
import { UsersService } from '../users.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  constructor(private usersService: UsersService) { }

  ngOnInit() {
  }

  users: Users[];
  getUsers() {
    this.usersService.getUsers()
    .subscribe((allUsers) => this.users = allUsers);
  }
}