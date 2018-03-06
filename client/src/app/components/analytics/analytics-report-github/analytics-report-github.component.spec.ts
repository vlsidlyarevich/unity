import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalyticsReportGithubComponent } from './analytics-report-github.component';

describe('AnalyticsReportGithubComponent', () => {
  let component: AnalyticsReportGithubComponent;
  let fixture: ComponentFixture<AnalyticsReportGithubComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnalyticsReportGithubComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnalyticsReportGithubComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
