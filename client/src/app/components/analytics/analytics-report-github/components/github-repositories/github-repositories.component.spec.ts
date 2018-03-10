import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GithubRepositoriesComponent } from './github-repositories.component';

describe('GithubRepositoriesComponent', () => {
  let component: GithubRepositoriesComponent;
  let fixture: ComponentFixture<GithubRepositoriesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GithubRepositoriesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GithubRepositoriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
