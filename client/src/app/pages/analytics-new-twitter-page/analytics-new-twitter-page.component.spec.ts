import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalyticsNewTwitterPageComponent } from './analytics-new-twitter-page.component';

describe('AnalyticsNewTwitterPageComponent', () => {
  let component: AnalyticsNewTwitterPageComponent;
  let fixture: ComponentFixture<AnalyticsNewTwitterPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnalyticsNewTwitterPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnalyticsNewTwitterPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
