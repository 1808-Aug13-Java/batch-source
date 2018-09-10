import { Component, OnInit } from '@angular/core';
import { Heroes} from '../mock-heroes';

@Component({
  selector: 'app-profiles',
  templateUrl: './profiles.component.html',
  styleUrls: ['./profiles.component.css']
})
export class ProfilesComponent implements OnInit {
  hero : Heroes = {
    id: 1,
    name: 'Artorias',
    class: 'Abysswalker',};

  constructor() { }

  ngOnInit() {
  }

}