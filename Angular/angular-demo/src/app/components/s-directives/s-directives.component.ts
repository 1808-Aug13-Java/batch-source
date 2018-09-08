import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-s-directives',
  templateUrl: './s-directives.component.html',
  styleUrls: ['./s-directives.component.css']
})
export class SDirectivesComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  condition: boolean = false;

  changeCondition(){
    this.condition = !this.condition;
  }

  courses = [{
    id: 6,
    name: 'history'
  },{
    id: 42,
    name: 'art'
  },{
    id: 15,
    name: 'french'
  }]

  time:string = 'noon';

  changeNight(){
    this.time = 'night';
  }

  changeDay(){
    this.time = 'day';
  }

}
