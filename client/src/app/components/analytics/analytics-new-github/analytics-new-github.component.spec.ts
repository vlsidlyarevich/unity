import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalyticsNewGithubComponent } from './analytics-new-github.component';

describe('AnalyticsNewGithubComponent', () => {
  let component: AnalyticsNewGithubComponent;
  let fixture: ComponentFixture<AnalyticsNewGithubComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnalyticsNewGithubComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnalyticsNewGithubComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
