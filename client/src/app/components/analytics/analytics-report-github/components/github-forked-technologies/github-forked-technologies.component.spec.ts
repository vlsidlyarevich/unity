import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GithubForkedTechnologiesComponent } from './github-forked-technologies.component';

describe('GithubForkedTechnologiesComponent', () => {
  let component: GithubForkedTechnologiesComponent;
  let fixture: ComponentFixture<GithubForkedTechnologiesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GithubForkedTechnologiesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GithubForkedTechnologiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
