import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'capitalA'
})
export class CapitalAPipe implements PipeTransform {

  transform(value: string, character: string): any {

    let newValue: string = "";
    for(let c of value){
      if(c === character){
        newValue += character.toUpperCase();
      }
      else{
        newValue += c;
      }
    }
    return newValue;
  }

}
