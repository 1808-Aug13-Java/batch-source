import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { HeroService }  from '../hero.service';

import {Hero} from '../hero';

@Component({
  selector: 'app-hereo-detail',
  templateUrl: './hereo-detail.component.html',
  styleUrls: ['./hereo-detail.component.css']
})
export class HereoDetailComponent implements OnInit {

  @Input() hero: Hero;
  constructor( private route: ActivatedRoute,
    private heroService: HeroService,
    private location: Location) { }

  ngOnInit() {
    this.getHero();
  }

  save(): void {
    this.heroService.updateHero(this.hero)
      .subscribe(() => this.goBack());
  }

  getHero(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.heroService.getHero(id)
      .subscribe(hero => this.hero = hero);
  }

  goBack(): void {
    this.location.back();
  }

}
