import { Component, OnInit } from '@angular/core';
import { Globals } from '../../data/Globals';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private globals: Globals) { }

  ngOnInit() {
  }


  // Used to iterate through a map by keys. 
  objectKeys = Object.keys;

  // The variable pointing to the global heroes map. 
  heroes = this.globals.heroes;

  // The list of the top heroes.
  topHeroes = this.globals.topHeroes;
}
