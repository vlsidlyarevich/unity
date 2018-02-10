import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalyticsResultsPageComponent } from './analytics-results-page.component';

describe('AnalyticsResultsPageComponent', () => {
  let component: AnalyticsResultsPageComponent;
  let fixture: ComponentFixture<AnalyticsResultsPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnalyticsResultsPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnalyticsResultsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
