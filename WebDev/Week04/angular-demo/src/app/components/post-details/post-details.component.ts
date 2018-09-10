import { Component, OnInit } from '@angular/core';
import { PostService } from '../../services/post.service';
import { ActivatedRoute } from '../../../../node_modules/@angular/router';

@Component({
  selector: 'app-post-details',
  templateUrl: './post-details.component.html',
  styleUrls: ['./post-details.component.css']
})
export class PostDetailsComponent implements OnInit {

  constructor(private postService: PostService, private route: ActivatedRoute) { }

  ngOnInit() {
		this.route.params.subscribe((param) =>
			this.currentPost.id = param['id']);
		console.log(this.currentPost.id);
		this.getPost(Number(this.currentPost.id));
  }
  
  // Uses the 'post' service that we made to get a Promise to get a 'Post'
  getPost(idParam: number) {
	  this.postService.getPost(idParam)
	  	.then((result) =>{ //		We use .then as the Promise is async. Therefore, we wait until it is done. 
			this.currentPost = result;
		})
		.catch((e) => console.log(e));
  }

  currentPost: Post = {
	  id: undefined,
	  userId: undefined, 
	  title: undefined,
	  body: undefined
  };

}