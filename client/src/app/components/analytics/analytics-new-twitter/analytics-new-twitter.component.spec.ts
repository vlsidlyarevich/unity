import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalyticsNewTwitterComponent } from './analytics-new-twitter.component';

describe('AnalyticsNewTwitterComponent', () => {
  let component: AnalyticsNewTwitterComponent;
  let fixture: ComponentFixture<AnalyticsNewTwitterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnalyticsNewTwitterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnalyticsNewTwitterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
