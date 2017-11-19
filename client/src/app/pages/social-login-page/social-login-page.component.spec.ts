import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SocialLoginPageComponent } from './social-login-page.component';

describe('SocialLoginPageComponent', () => {
  let component: SocialLoginPageComponent;
  let fixture: ComponentFixture<SocialLoginPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SocialLoginPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SocialLoginPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
