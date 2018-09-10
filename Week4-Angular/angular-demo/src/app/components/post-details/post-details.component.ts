import { Component, OnInit } from '@angular/core';
import { PostService } from '../../services/post.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-post-details',
  templateUrl: './post-details.component.html',
  styleUrls: ['./post-details.component.css']
})
export class PostDetailsComponent implements OnInit {

  constructor(private postService: PostService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(param=>
    this.currentPost.id = param['id'])
    console.log(this.currentPost.id);
    this.getPost(Number(this.currentPost.id));
  }

  currentPost: Post = {
    id: undefined,
    userId: undefined,
    title: undefined,
    body: undefined
  }

  getPost(idParam: number){
    this.postService.getPost(idParam)
    .then((res)=>{
      this.currentPost = res;
    })
    .catch((e)=>console.log(e));
  }

}
