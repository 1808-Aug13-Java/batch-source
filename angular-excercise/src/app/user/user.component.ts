import { Component, OnInit } from '@angular/core';
import { PostService } from '../post.service';
// import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
 
  constructor(private postService: PostService) { }

  ngOnInit() {
    
  }
  postman: Post[];
  // currentPost: Post = {
  //   "id": undefined,
  //   "name": undefined,
  //   "username": undefined,
  //   "email": undefined,
  //   "address": {
  //     "street": undefined,
  //     "suite": undefined,
  //     "city": undefined,
  //     "zipcode": undefined,
  //     "geo": {
  //       "lat": undefined,
  //       "lng": undefined
  //     }
  //   },
  //   "phone": undefined,
  //   "website": undefined,
  //   "company": {
  //     "name": undefined,
  //     "catchPhrase": undefined,
  //     "bs": undefined
  //   }
  // }

  getPosts(){
    this.postService.getPosts()
    .subscribe((allPosts)=>{this.postman = allPosts})
    
  }

}