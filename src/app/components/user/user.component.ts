import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})

/*
5. User Component:
Create a component which makes an API call to https://jsonplaceholder.typicode.com/users 
and populates the page with the fake users 
*/
export class UserComponent implements OnInit {

  constructor(private userService: UserService) { } // dependency injection

  ngOnInit() {
    // this.route.params.subscribe(param =>
    //   this.currentUser.id = param['id']) // this is an observable, so we can subscribe to it and track as it changes
    //   console.log(this.currentUser.id);
    //   this.getUser(Number(this.currentUser.id));
  }

  // currentUser: Userr = {
  //     id: undefined,
  //     name: undefined,
  //     username: undefined,
  //     email: undefined,
  //     address: {
  //       street: undefined,
  //       suite: undefined,
  //       city: undefined,
  //       zipcode: undefined,
  //       geo: {
  //         lat: undefined,
  //         lng: undefined
  //       }
  //     },
  //     phone: undefined,
  //     website: undefined,
  //     company: {
  //       name: undefined,
  //       catchphrase: undefined,
  //       bs: undefined
  //     }
  // }

  // getUser(idParam: number){ // this is going to give us a Promise which means we can give it behaviour when it succeeds or fails
  //   this.userService.getUser(idParam)
  //   .then((res)=>{ // .then because we need to know when asynchronous even will occur before this happens
  //     this.currentUser = res;
  //   })
  //   .catch((e)=> console.log(e));
  // }

  users: User[] = [];

  getUsers(){
    this.userService.getUsers().subscribe((allUsers) => {this.users = allUsers});
  }

}
