import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TwitterProfileComponent } from './twitter-profile.component';

describe('TwitterProfileComponent', () => {
  let component: TwitterProfileComponent;
  let fixture: ComponentFixture<TwitterProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TwitterProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TwitterProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
