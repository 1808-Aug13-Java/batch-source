import { InMemoryDbService } from 'angular-in-memory-web-api';
 
export class InMemoryDataService implements InMemoryDbService {
  createDb() {
    const heroes =  [
      { id: 11, name: 'Groot' },
      { id: 12, name: 'Rocket Racoon' },
      { id: 13, name: 'Gamora' },
      { id: 14, name: 'Drax the Destroyer' },
      { id: 15, name: 'Adam Warlock' },
      { id: 16, name: 'Yondu' },
      { id: 17, name: 'Quasar' },
      { id: 18, name: 'Star-Lord' },
      { id: 19, name: 'Mantis' },
      { id: 20, name: 'Cosmo the Space Dog' }
    ];
    return {heroes};
  }
}