import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'convertToSpace'
})
export class ConvertToSpacePipe implements PipeTransform {

  transform(value: string, character: any): any {
    return value.replace(character, ' ');
  }

}
