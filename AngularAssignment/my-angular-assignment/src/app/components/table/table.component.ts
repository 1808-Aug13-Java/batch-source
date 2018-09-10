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
  

  people = [{ first: "john", last: "patterson", email: "jpatt213@gmail.com", birthday: "5/12/1981" },{
     first: "ban", last: "leo", email: "banleo0912@yahoo.com", birthday: "8/10/1998" },{
     first: "jaina", last: "proudmoore", email: "jainaproud12@gmail.com", birthday: "11/09/1996" },{
     first: "cjay", last: "pj", email: "cjayride@outlook.com", birthday: "3/8/1993" },{
     first: "lily", last: "huerta", email: "lilyhuerta101@gmail.com", birthday: "9/23/2096" },{
     first: "cooper", last: "gege", email: "cooperg@yahoo.com", birthday: "10/11/1990" }]

}