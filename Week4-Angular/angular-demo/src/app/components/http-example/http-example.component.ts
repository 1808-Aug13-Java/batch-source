import { Component, OnInit } from '@angular/core';
import { PostService } from '../../services/post.service';

@Component({
  selector: 'app-http-example',
  templateUrl: './http-example.component.html',
  styleUrls: ['./http-example.component.css']
})
export class HttpExampleComponent implements OnInit {

  constructor(private postService: PostService) { }

  ngOnInit() {
  }

  posts: Post[];

  getPosts(){
    this.postService.getPosts()
      .subscribe((allPosts) => {this.posts = allPosts});
  }

}
