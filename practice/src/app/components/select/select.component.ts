import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.css']
})
export class SelectComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }
  colors: string[] = ['blue','red','green'];
  animals: string[] = ['iguana','beholder','newt'];
  days: string[] = ['Morndas','Tirdas','Middas','Turdas','Fredas','Loredas','Sundas'];
  items: string[] = ['string-cheese','gala-apples','ground-beef'];
  empty():void{}
}
