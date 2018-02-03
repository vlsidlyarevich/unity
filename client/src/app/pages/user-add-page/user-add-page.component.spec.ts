import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserAddPageComponent } from './user-add-page.component';

describe('UserAddPageComponent', () => {
  let component: UserAddPageComponent;
  let fixture: ComponentFixture<UserAddPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserAddPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserAddPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
