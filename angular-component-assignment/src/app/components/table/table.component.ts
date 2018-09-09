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
  bootstrapOption: string = "Bootstrap";
  show: boolean = true;

  people = [{ firstName: "crin", lastName: "toghill", email: "ctoghill0@mysql.com", birthday: "3/28/1993" },
  { firstName: "berte", lastName: "sprigings", email: "bsprigings1@dailymotion.com", birthday: "8/9/1996" },
  { firstName: "emmanuel", lastName: "duffit", email: "eduffit2@ask.com", birthday: "10/25/1998" },
  { firstName: "lilah", lastName: "yeo", email: "lyeo3@amazon.co.uk", birthday: "3/8/1993" },
  { firstName: "whit", lastName: "chowne", email: "wchowne4@1und1.de", birthday: "6/13/1996" },
  { firstName: "lin", lastName: "cutsforth", email: "lcutsforth5@nyu.edu", birthday: "8/4/1992" },
  { firstName: "vinni", lastName: "vevers", email: "vvevers6@mail.ru", birthday: "12/29/1990" },
  { firstName: "dominica", lastName: "earley", email: "dearley7@fc2.com", birthday: "7/20/1997" },
  { firstName: "ludovico", lastName: "rikel", email: "lrikel8@oaic.gov.au", birthday: "12/31/1996" },
  { firstName: "marshal", lastName: "copner", email: "mcopner9@ifeng.com", birthday: "3/21/1992" }]

  changeBootstrapOption() {
    this.show = !this.show;
    if (this.bootstrapOption == "Bootstrap") {
      this.bootstrapOption = "HTML";
    } else {
      this.bootstrapOption = "Bootstrap";
    }
  }
}
