import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  constructor() { }

   ngOnInit() {
  }
  

  people = [{ first: "crin", last: "toghill", email: "ctoghill0@mysql.com", birthday: "3/28/1993" },{
     first: "berte", last: "sprigings", email: "bsprigings1@dailymotion.com", birthday: "8/9/1996" },{
     first: "emmanuel", last: "duffit", email: "eduffit2@ask.com", birthday: "10/25/1998" },{
     first: "lilah", last: "yeo", email: "lyeo3@amazon.co.uk", birthday: "3/8/1993" },{
     first: "whit", last: "chowne", email: "wchowne4@1und1.de", birthday: "6/13/1996" },{
     first: "lin", last: "cutsforth", email: "lcutsforth5@nyu.edu", birthday: "8/4/1992" },{
     first: "vinni", last: "vevers", email: "vvevers6@mail.ru", birthday: "12/29/1990" },{
     first: "dominica", last: "earley", email: "dearley7@fc2.com", birthday: "7/20/1997" },{
     first: "ludovico", last: "rikel", email: "lrikel8@oaic.gov.au", birthday: "12/31/1996" },{
     first: "marshal", last: "copner", email: "mcopner9@ifeng.com", birthday: "3/21/1992" }]

}