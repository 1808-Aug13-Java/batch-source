import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'capitalize'
})
export class CapitalizePipe implements PipeTransform {

  transform(value: string, args?: any): any {
    let nextUpper = true;
    let ret = value;
    for (let i = 0; i < value.length; i++){
      if(nextUpper){
        nextUpper = false;
        ret = ret.substr(0, i) + ret.charAt(i).toUpperCase() + ret.substr(i + 1);
      } else if (value.charAt(i) == ' '){
        nextUpper = true;
      }
    }

    return ret;
  }

}
