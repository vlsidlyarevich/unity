/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { GitResultComponent } from './git-result.component';

describe('GitResultComponent', () => {
  let component: GitResultComponent;
  let fixture: ComponentFixture<GitResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GitResultComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GitResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
