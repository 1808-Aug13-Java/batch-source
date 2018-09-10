import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-component2',
  templateUrl: './component2.component.html',
  styleUrls: ['./component2.component.css']
})
export class Component2Component implements OnInit {

  constructor() { }
  name:String;
  email:String;
  birthdate:String;
  check:boolean = false;
  ngOnInit() {
  }
  hideMe(): void {
    this.check = !this.check;
  }


}
