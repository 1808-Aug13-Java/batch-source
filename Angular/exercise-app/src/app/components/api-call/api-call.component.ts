import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-api-call',
  templateUrl: './api-call.component.html',
  styleUrls: ['./api-call.component.css']
})
export class ApiCallComponent implements OnInit {

  constructor(private userService: UserService) { }

  ngOnInit() {
  }

  // users: User[];
  user: User;

  // getUsers(){
  //   this.userService.getUsers().subscribe((allUsers) => {this.users = allUsers})
  // }
  showUsers() {
    this.userService.getUsers()
      .subscribe((data: User) => this.user = {
          id: data['id'],
          name:  data['name'],
          username: data['username'],
          email: data['email'],
          address: data['address'],
          phone: data['phone'],
          website: data['website'],
          company: data['company']
      });
  }
}
