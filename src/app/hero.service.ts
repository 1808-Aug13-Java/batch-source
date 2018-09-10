import { Injectable } from '@angular/core';
//getting data
import { Hero } from './hero';
import { HEROES } from './mock-heroes';
//we must make getheroes return an Observable bcuz rn its 
//synchronous but browser data is asynchronous.
//3 ways to make our service async is rtn Observable or Promise
//or take in a callback
import { Observable, of } from 'rxjs';
//now we have a messages service
import { MessageService } from './message.service';


@Injectable({
  providedIn: 'root'
})
export class HeroService {
  constructor( private messageService: MessageService) { }

  // getHeroes(): Hero[] {
  // return HEROES;
  // }
  //must do <> because observable's syntax is Observable<T>
  getHeroes(): Observable<Hero[]>{  
    this.messageService.add('HeroService: fetched heroes');
    return of (HEROES);   //using "of" that we imported
  }

  getHero(id: number): Observable<Hero> {
    // TODO: send the message _after_ fetching the hero
    this.messageService.add(`HeroService: fetched hero id=${id}`);
    return of(HEROES.find(hero => hero.id === id));
  }
}
