import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'capitalizeFirstLetter'
})
export class CapitalizeFirstLetterPipe implements PipeTransform {

  result: string
  transform(value: string): string {
    let valueArr: string[] = value.split('-');
    for (let i in valueArr) {
      valueArr[i] = valueArr[i].charAt(0).toUpperCase() + valueArr[i].substr(1);
    }
    return valueArr.join(' ');
  }
}