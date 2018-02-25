import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalyticsNewGithubPageComponent } from './analytics-new-github-page.component';

describe('AnalyticsNewGithubPageComponent', () => {
  let component: AnalyticsNewGithubPageComponent;
  let fixture: ComponentFixture<AnalyticsNewGithubPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnalyticsNewGithubPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnalyticsNewGithubPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
