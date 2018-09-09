import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'capitalizeFirstLetter'
})
export class CapitalizeFirstLetterPipe implements PipeTransform {

  transform(value: any): string {
    let result = value[0].toUpperCase();

    for (let character = 1; character < value.length; character++) {
      if (value[character - 1] === ' ') {
        result += value[character].toUpperCase();
      } else {
        result += value[character];
      }
    }
    return result;
  }

}
