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

  // Used to iterate through a map by keys. 
  objectKeys = Object.keys;

  // The variable pointing to the global heroes map. 
  heroes = this.globals.heroes;

  // The currently selected hero
  currentHeroId: number;

  // True if the hero details should be displayed at the bottom. False by default. 
  displayHero: boolean = false;

  // When clicked, shows details at the bottom, and sets the curent hero id. 
  clickRow(heroId) {
	this.currentHeroId = heroId;
	this.displayHero = true;
  }
}
