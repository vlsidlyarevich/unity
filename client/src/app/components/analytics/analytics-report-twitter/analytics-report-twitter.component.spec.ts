import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalyticsReportTwitterComponent } from './analytics-report-twitter.component';

describe('AnalyticsReportTwitterComponent', () => {
  let component: AnalyticsReportTwitterComponent;
  let fixture: ComponentFixture<AnalyticsReportTwitterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnalyticsReportTwitterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnalyticsReportTwitterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
