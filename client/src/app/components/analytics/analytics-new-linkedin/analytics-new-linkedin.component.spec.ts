import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalyticsNewLinkedinComponent } from './analytics-new-linkedin.component';

describe('AnalyticsNewLinkedinComponent', () => {
  let component: AnalyticsNewLinkedinComponent;
  let fixture: ComponentFixture<AnalyticsNewLinkedinComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnalyticsNewLinkedinComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnalyticsNewLinkedinComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
