import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'capitalizeName'
})
export class CapitalizeNamePipe implements PipeTransform {

  transform(value: string): string {
    return value.charAt(0).toUpperCase() + value.substring(1);
  }

}
