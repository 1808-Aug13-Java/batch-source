import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  // Makes a new private variable called postService. 
  constructor(private postService: UserService) { }

  // Upon initial load, get the users from the server. 
  ngOnInit() {
	this.getUsers();
  }

  users: User[];

  getUsers(){
	  // First, uses the postService, which is our custom service, to request an array of Post
	  // which is our custom interface to define the stucture of incomming JSON. 

	  // We then subscribe to the Returned Observable that get's an array of Post. 
	  // A callback function is provided to define behavior when a result is returned. 
	  // In this instance, we get 1 array of Post. 
	  this.postService.getUsers()
	  	.subscribe((allUsers) => {this.users = allUsers});
  }

}
