import { Component, OnInit } from '@angular/core';
@Component({
  selector: 'app-component4',
  templateUrl: './component4.component.html',
  styleUrls: ['./component4.component.css']
})
export class Component4Component implements OnInit {

  constructor() { }

  people: { fname: string, lname: string, email: string, birthday: string }[] = [ { "fname": "jorge", "lname": "sagrero"
  , "email": "jsagrero@gmail.com", "birthday": "11/25/1302"},{ "fname": "maria", "lname": "sagrero"
  , "email": "msagrero@gmail.com", "birthday": "11/25/1999"},{ "fname": "manuel", "lname": "sagrero"
  , "email": "manuelsagrero@gmail.com", "birthday": "11/25/1303"}];

  ngOnInit() {
  }

  check: boolean = false;
  hideMe(): void {
    this.check = !this.check;
  }
  

}
