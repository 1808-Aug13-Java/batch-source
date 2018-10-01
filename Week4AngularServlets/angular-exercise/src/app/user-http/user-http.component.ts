import { Component, OnInit } from '@angular/core';
import { UserServiceService } from '../user-service.service';

@Component({
  selector: 'app-user-http',
  templateUrl: './user-http.component.html',
  styleUrls: ['./user-http.component.css']
})
export class UserHttpComponent implements OnInit {

  constructor(private userService: UserServiceService) { }

  ngOnInit() {
    this.getPosts();
  }

  users: User[];

  getPosts() {
    this.userService.getUsers().subscribe((allUsers) => {
      this.users = allUsers;
    });
  }
}
