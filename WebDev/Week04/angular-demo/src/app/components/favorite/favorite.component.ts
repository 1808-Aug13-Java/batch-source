import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-favorite',
  templateUrl: './favorite.component.html',
  styleUrls: ['./favorite.component.css']
})
export class FavoriteComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  clazz: string = "glyphicon glyphicon-star";

  clickStar() {
	  if (this.clazz === "glyphicon glyphicon-star") {
		  this.clazz = "glyphicon glyphicon-star-empty";
	  } else {
		  this.clazz = "glyphicon glyphicon-star";
	  }
  }
  
  isFavorite: boolean = false;

  // Changes is favorite, which is used to change the class of a star
  onClickChange() {
	  this.isFavorite = !this.isFavorite;
  }
}
