import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Component6Component } from './component6.component';

describe('Component6Component', () => {
  let component: Component6Component;
  let fixture: ComponentFixture<Component6Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Component6Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Component6Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
