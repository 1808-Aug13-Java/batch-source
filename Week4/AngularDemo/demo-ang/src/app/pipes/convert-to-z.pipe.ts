import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'convertToZ'
})
export class ConvertToZPipe implements PipeTransform {

  transform(val: string, toAdd: number): any {
    return Number(val)+toAdd;
  }

}
