import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'propercasing'
})
export class PropercasingPipe implements PipeTransform {

  transform(value:string): string{
    var strArr:String[] = value.split(' ');
    var strReturn:string='';
    for(let str of strArr){
      let str2:String = str.toLocaleLowerCase();
      strReturn += str2.replace(str2.charAt(0),str2.charAt(0).toUpperCase())+" ";
    }
    return strReturn;
  }

}
