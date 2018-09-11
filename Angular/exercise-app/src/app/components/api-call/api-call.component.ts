import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';

@Component({
  selector: 'app-api-call',
  templateUrl: './api-call.component.html',
  styleUrls: ['./api-call.component.css']
})
export class ApiCallComponent implements OnInit {

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.getUsers();
  }

  users: User[];
  user: User;

  // getUsers(){
  //   this.userService.getUsers().subscribe((allUsers) => {this.users = allUsers})
  // }
  getUsers(): void {
    this.userService.getUsers()
      .subscribe(users => this.users = users);
  }
}
