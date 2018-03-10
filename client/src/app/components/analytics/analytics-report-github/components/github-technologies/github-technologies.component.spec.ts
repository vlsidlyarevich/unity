import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GithubTechnologiesComponent } from './github-technologies.component';

describe('GithubTechnologiesComponent', () => {
  let component: GithubTechnologiesComponent;
  let fixture: ComponentFixture<GithubTechnologiesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GithubTechnologiesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GithubTechnologiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
