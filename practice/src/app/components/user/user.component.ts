import { Component, OnInit } from '@angular/core';
import { PostService } from '../../post.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  constructor(private postService: PostService) { }

  ngOnInit() {
  }

  users: User[];

  getUsers(){
    this.postService.getUsers()
      .subscribe((allPosts) => {this.users=allPosts})
  }

}
