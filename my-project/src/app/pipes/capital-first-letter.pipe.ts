import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'capitalFirstLetter'
})
export class CapitalFirstLetterPipe implements PipeTransform {

  transform(value: string): any {

    let firstLetter = value.charAt(0) + "";
    firstLetter = firstLetter.toUpperCase();

    firstLetter = firstLetter + value.substring(1, value.length)
    return firstLetter;
  }

}
