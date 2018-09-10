import { Component, OnInit } from '@angular/core';
import {Hero} from '../hero'; //dont do /hero.ts
// import { HEROES } from '../mock-heroes';
import { HeroService } from '../hero.service';
  //import our service that has the data already instead
    //of importing data into our components
//
@Component({
  selector: 'app-heroes',   //the selector is the 
                        //directive (custom html) element
                        //identifying the component
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.css']
})
export class HeroesComponent implements OnInit {

  //adding a "property"/member variable
  // hero: Hero = {
  //   id: 1,
  //   name: "Windstorm"
  // }
  //adding a property array heroes that'll hold the imported
  // heroes = HEROES;
  heroes:Hero[];
  // selectedHero: Hero;

  constructor(private heroService: HeroService) { }

  ngOnInit() {
    this.getHeroes();
  }

  // onSelect(hero){
  //   this.selectedHero = hero;
  // }

  getHeroes():void{
    //now no ood bcuz rting Observable<Heroes[]>
    // this.heroes = this.heroService.getHeroes();
    this.heroService.getHeroes()
      .subscribe( heroes => this.heroes = heroes);
  }
}
