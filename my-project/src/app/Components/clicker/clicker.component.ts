import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-clicker',
  templateUrl: './clicker.component.html',
  styleUrls: ['./clicker.component.css']
})
export class ClickerComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  count = 0;
  isFactorFive: boolean = false;

  increment(inc: number){
    this.count += inc;
  }

  onIncrementChange(){
    if(this.count % 5 === 0)
    {
      this.isFactorFive = true;
    }
    else{
      this.isFactorFive = false;
    }
  }

}
