import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { ActivatedRoute } from '../../../../node_modules/@angular/router';
import { getDefaultService } from '../../../../node_modules/@types/selenium-webdriver/opera';

@Component({
  selector: 'app-fake-users',
  templateUrl: './fake-users.component.html',
  styleUrls: ['./fake-users.component.css']
})
export class FakeUsersComponent implements OnInit {

  constructor(private userService: UserService, private route: ActivatedRoute) { }

  ngOnInit() {
  }

  users: User[];

  getUsers()
  {
    this.userService.getUsers()
    .subscribe((allUsers)=>{this.users = allUsers})
  }
}
