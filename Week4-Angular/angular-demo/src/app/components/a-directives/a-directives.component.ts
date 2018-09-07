import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-a-directives',
  templateUrl: './a-directives.component.html',
  styleUrls: ['./a-directives.component.css']
})
export class ADirectivesComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  colors: string[] = ['blue','green','red','yellow'];
  selectedColor: string;

}
