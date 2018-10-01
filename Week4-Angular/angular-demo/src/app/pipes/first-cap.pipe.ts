import { Pipe, PipeTransform } from "@angular/core";


@Pipe({
    name: 'firstCap'
})
export class FirstCapPipe implements PipeTransform{


    transform(value:string): string{
        let wordArr: string[] = value.split(' ');
        for(let i in wordArr){
            wordArr[i] = wordArr[i].charAt(0).toUpperCase() + wordArr[i].substring(1);
        }
        return wordArr.join(' ');
    }

}