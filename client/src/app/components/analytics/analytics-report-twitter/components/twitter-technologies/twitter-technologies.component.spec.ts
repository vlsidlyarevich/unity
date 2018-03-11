import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TwitterTechnologiesComponent } from './twitter-technologies.component';

describe('TwitterTechnologiesComponent', () => {
  let component: TwitterTechnologiesComponent;
  let fixture: ComponentFixture<TwitterTechnologiesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TwitterTechnologiesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TwitterTechnologiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
