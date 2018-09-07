import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ADirectivesComponent } from './a-directives.component';

describe('ADirectivesComponent', () => {
  let component: ADirectivesComponent;
  let fixture: ComponentFixture<ADirectivesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ADirectivesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ADirectivesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
