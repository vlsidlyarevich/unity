import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalyticsReportsComponent } from './analytics-reports.component';

describe('AnalyticsReportsComponent', () => {
  let component: AnalyticsReportsComponent;
  let fixture: ComponentFixture<AnalyticsReportsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnalyticsReportsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnalyticsReportsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
