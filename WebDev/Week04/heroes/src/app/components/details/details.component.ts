import { Component, OnInit } from '@angular/core';
import { Globals } from '../../data/Globals';
import { ActivatedRoute } from '../../../../node_modules/@angular/router';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {

  constructor(private globals: Globals, private route: ActivatedRoute) { }

  ngOnInit() {
	  this.route.params.subscribe(params => {
		this.currentHeroId = +params['id'];
	 });
  }

  // The current hero number. 
  currentHeroId: number;

  // Get the global heroes map. 
  heroes = this.globals.heroes;
}
