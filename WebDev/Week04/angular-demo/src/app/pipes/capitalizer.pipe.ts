import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'capitalizer'
})
export class CapitalizerPipe implements PipeTransform {

	// this is a pipe that is used to create 
  transform(value: string, args?: any): string {
	let strings = value.split(" ");
	
	for (let i in strings) {
		// The rest isn't necessary as aparently substring(1) doesn't throw exception on IndexOutOfBounds. 
		// if (strings[i].length == 1) {
		// 	strings[i] = strings[i].toUpperCase();
		// } else {
			strings[i] = strings[i].substring(0, 1).toUpperCase() + strings[i].substring(1);
		// }
		
	}

    return strings.join(" ");
  }

}
