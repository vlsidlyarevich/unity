import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TwitterSubscriptionsComponent } from './twitter-subscriptions.component';

describe('TwitterSubscriptionsComponent', () => {
  let component: TwitterSubscriptionsComponent;
  let fixture: ComponentFixture<TwitterSubscriptionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TwitterSubscriptionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TwitterSubscriptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
