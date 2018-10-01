import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SDirectivesComponent } from './s-directives.component';

describe('SDirectivesComponent', () => {
  let component: SDirectivesComponent;
  let fixture: ComponentFixture<SDirectivesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SDirectivesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SDirectivesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
