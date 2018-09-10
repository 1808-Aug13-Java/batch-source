import { Component, OnInit } from '@angular/core';
import { Globals } from '../../data/Globals';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  constructor(private globals: Globals) { }

  ngOnInit() {
  }

  objectKeys = Object.keys;
  heroes = this.globals.heroes;


  currentHeroId: number;

  displayHero: boolean = false;

  clickRow(heroId) {
	this.currentHeroId = heroId;
	this.displayHero = true;
  }
}
