import { Component, OnInit } from '@angular/core';
import { PopulateService } from '../populate.service'
@Component({
  selector: 'app-component5',
  templateUrl: './component5.component.html',
  styleUrls: ['./component5.component.css']
})
export class Component5Component implements OnInit {

  constructor(private postService: PopulateService) { }

  ngOnInit() {
  
  }
  posts: Post[];
  getUsers() {
    this.postService.getPosts().subscribe((allPosts) => this.posts = allPosts);
    // this.postService.getPosts()
    // .subscribe((allPosts) => this.posts = allPosts);
  }

  

}
