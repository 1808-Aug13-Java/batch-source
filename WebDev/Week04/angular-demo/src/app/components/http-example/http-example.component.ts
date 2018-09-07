import { Component, OnInit } from '@angular/core';
import { PostService } from '../../services/post.service';

@Component({
  selector: 'app-http-example',
  templateUrl: './http-example.component.html',
  styleUrls: ['./http-example.component.css']
})
export class HttpExampleComponent implements OnInit {

	// Makes a new private variable called postService. 
  constructor(private postService: PostService) { }

  ngOnInit() {
  }

  posts: Post[];

  getPosts(){
	  // First, uses the postService, which is our custom service, to request an array of Post
	  // which is our custom interface to define the stucture of incomming JSON. 

	  // We then subscribe to the Returned Observable that get's an array of Post. 
	  // A callback function is provided to define behavior when a result is returned. 
	  // In this instance, we get 1 array of Post. 
	  this.postService.getPosts()
	  	.subscribe((allPosts) => {this.posts = allPosts});
  }
}
