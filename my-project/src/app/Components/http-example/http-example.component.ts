import { Component, OnInit } from '@angular/core';
import { ServiceService } from '../../services/service.service';

@Component({
  selector: 'app-http-example',
  templateUrl: './http-example.component.html',
  styleUrls: ['./http-example.component.css']
})
export class HttpExampleComponent implements OnInit {

  constructor(private ServiceService: ServiceService) { }

  ngOnInit() {
  }

  posts: Post[];

  getPosts()
  {
    this.ServiceService.getPosts()
    .subscribe((allPosts)=>{this.posts = allPosts})
  }

}
