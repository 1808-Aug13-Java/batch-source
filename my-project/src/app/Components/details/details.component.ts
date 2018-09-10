import { Component, OnInit } from '@angular/core';
import { ServiceService } from '../../services/service.service';
import { ActivatedRoute } from '../../../../node_modules/@angular/router';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {

  constructor(private serviceService: ServiceService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(param=>this.currentPost.id = param['id']);
    console.log(this.currentPost.id);
    this.getPost(Number(this.currentPost.id));
  }

  currentPost: Post = {
    id: undefined,
    userId: undefined,
    title: undefined,
    body: undefined
  }

  getPost(id:number){
    this.serviceService.getPostById(id).then((res)=>{this.currentPost = res;}).catch((e)=>console.log(e))
  }



}
