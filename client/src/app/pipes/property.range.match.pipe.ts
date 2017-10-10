import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'matchesPropertyInRange' })
export class PropertyRangeMatchPipe implements PipeTransform {
  transform(items: Array<any>, property: string, lowerBound: string, upperBound: string): Array<any> {
    if (items) {
      return items.filter(item => (lowerBound < item[property]) && (item[property] < upperBound));
    }
    return items;
  }
}
