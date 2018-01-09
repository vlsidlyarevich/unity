import { NgModule } from '@angular/core';
import { ArraySortByPropertyPipe } from '../array.sort.by.property.pipe';
import { PropertyMatchPipe } from '../property.match.pipe';
import { PropertyRangeMatchPipe } from '../property.range.match.pipe';
import { ImagePipe } from '../image.pipe';
import { SafeUrlPipe } from '../safe.url.pipe';

@NgModule({
  imports: [],
  declarations: [ArraySortByPropertyPipe,
    PropertyMatchPipe,
    PropertyRangeMatchPipe,
    ImagePipe,
    SafeUrlPipe],
  exports: [ArraySortByPropertyPipe,
    PropertyMatchPipe,
    PropertyRangeMatchPipe,
    ImagePipe,
    SafeUrlPipe]
})
export class PipeModule {
  static forRoot() {
    return {
      ngModule: PipeModule,
      providers: [],
    };
  }
}
