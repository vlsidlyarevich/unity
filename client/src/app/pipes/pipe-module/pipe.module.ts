import { NgModule } from '@angular/core';
import { ArraySortByPropertyPipe } from '../array.sort.by.property.pipe';
import { PropertyMatchPipe } from '../property.match.pipe';
import { PropertyRangeMatchPipe } from '../property.range.match.pipe';

@NgModule({
  imports: [],
  declarations: [ArraySortByPropertyPipe,
    PropertyMatchPipe,
    PropertyRangeMatchPipe],
  exports: [ArraySortByPropertyPipe,
    PropertyMatchPipe,
    PropertyRangeMatchPipe]
})
export class PipeModule {
  static forRoot() {
    return {
      ngModule: PipeModule,
      providers: [],
    };
  }
}
