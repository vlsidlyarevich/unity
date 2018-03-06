import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalyticsReportPageComponent } from './analytics-report-page.component';

describe('AnalyticsReportPageComponent', () => {
  let component: AnalyticsReportPageComponent;
  let fixture: ComponentFixture<AnalyticsReportPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnalyticsReportPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnalyticsReportPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
