import { Component, OnInit } from '@angular/core';
import { UserServeService } from '../../services/user-serve.service';
import { Subscriber } from 'rxjs';


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  constructor(private userService: UserServeService) { }

  ngOnInit() {
  }

  users: User[];
  getUsers() {
    this.userService.getUsers().subscribe((allUsers) => {this.users = allUsers; } );
  }
}
