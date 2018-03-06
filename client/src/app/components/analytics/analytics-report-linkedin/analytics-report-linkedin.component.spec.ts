import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalyticsReportLinkedinComponent } from './analytics-report-linkedin.component';

describe('AnalyticsReportLinkedinComponent', () => {
  let component: AnalyticsReportLinkedinComponent;
  let fixture: ComponentFixture<AnalyticsReportLinkedinComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnalyticsReportLinkedinComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnalyticsReportLinkedinComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
