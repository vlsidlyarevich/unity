import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'matchesProperty' })
export class PropertyMatchPipe implements PipeTransform {
  transform(items: Array<any>, property: string, value: string): Array<any> {
    if(items){
      return items.filter(item => item[property] === value);
    }
    return items;
  }
}
