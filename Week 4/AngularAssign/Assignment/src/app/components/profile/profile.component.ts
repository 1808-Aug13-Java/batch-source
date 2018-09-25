import { Component } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent{

  // constructor() { }


  hidden:boolean =false;
  buttontext:string="show";
  name;
  email;
bye(){
  this.hidden=!this.hidden;
  if(this.buttontext==="show"){
    this.buttontext = "hide";
  }
  else{
    this.buttontext="show";
  }
  
}

}
