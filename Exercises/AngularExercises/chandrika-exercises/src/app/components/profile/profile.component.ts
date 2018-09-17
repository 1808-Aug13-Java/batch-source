import { Component, OnInit } from '@angular/core';
import { element } from '../../../../node_modules/@angular/core/src/render3/instructions';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html'
})
export class ProfileComponent {
  user = {name:'Chandrika Sinha', email:'sinhac.rpi@gmail.com'};
  // state = document.getElementById("state").innerText;
  toggle() {
    let element = document.getElementById("state");
    let info = document.getElementById("profile");
    if (element.innerText=="hide") {
      info.style.visibility = "hidden";
      element.innerText="show";
    } else {
      info.style.visibility = "visible";
      element.innerText="hide";
    }
  }
}
