import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: "sortByProperty"
})
export class ArraySortByPropertyPipe implements PipeTransform {
  transform(items: Array<any>, property: string): Array<string> {
    if (items) {
      items.sort((a: any, b: any) => {
        if (a[property] < b[property]) {
          return 1;
        } else if (a[property] > b[property]) {
          return -1;
        } else {
          return 0;
        }
      });
    }
    return items;
  }
}
