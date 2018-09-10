import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-people',
  templateUrl: './people.component.html',
  styleUrls: ['./people.component.css']
})
export class PeopleComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  people: Person[] = [{firstName: "cirey" ,lastName: "francis", email: "cireyFrancis@gmail.com", birthday: new Date('1995-09-06') },
  {firstName: "Max" ,lastName: "Mooney", email: "maxEffect@gmail.com", birthday: new Date },
  {firstName: "Freddy" ,lastName: "Mercury", email: "SuperSonicMan@gmail.com", birthday: new Date }];

  format:string = "";

  switchFormat(){
    if(this.format === ""){
      this.format = "table";
    }
    else{
      this.format = "";
    }
  }

}
