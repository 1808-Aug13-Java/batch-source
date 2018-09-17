import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  constructor(private uesrService: UserService) { }

  ngOnInit() {
  }

  users: User[];
  getUsers() {
    this.uesrService.getUsers()
    .subscribe((allUsers) => this.users = allUsers);
  }
}