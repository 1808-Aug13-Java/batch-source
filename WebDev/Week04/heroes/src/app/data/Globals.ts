import { Injectable } from '@angular/core';

// This class will hold data that is global in the scope of the application. 
@Injectable()
export class Globals {
  role: string = 'test';

  // The currently selected hero
  currentHeroId = 11;

  // Saves a function that returns an array of the keys. This is required as 
  // to parse through the heroes map. 
  objectKeys = Object.keys;

  // A Map of ID's to hero names. 
  heroes = {
	11: "Mr. Nice", 
	12: "Narco",
	13: "Bombasto",
	14: "Celeritas", 
	15: "Magneta",
	16: "RubberMan", 
	17: "Dynama", 
	18: "Dr IQ",
	19: "Magma",
	20: "Tornado"
  }
  


}