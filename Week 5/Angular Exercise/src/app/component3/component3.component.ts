import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-component3',
  templateUrl: './component3.component.html',
  styleUrls: ['./component3.component.css']
})
export class Component3Component implements OnInit {

  constructor() { }

  animals: String [] = ['cat','dog','bird','cow','shark']
  colors: String [] = ['magenta','blue','red','cyan','green']
  days: String [] = ['monday','tuesday','wednesday','thursday','friday']
  check1: boolean = true;
  check2: boolean = false;
  check3: boolean = false;
  
  ngOnInit() {
  }

}
