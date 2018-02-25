import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalyticsNewLinkedinPageComponent } from './analytics-new-linkedin-page.component';

describe('AnalyticsNewLinkedinPageComponent', () => {
  let component: AnalyticsNewLinkedinPageComponent;
  let fixture: ComponentFixture<AnalyticsNewLinkedinPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnalyticsNewLinkedinPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnalyticsNewLinkedinPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
