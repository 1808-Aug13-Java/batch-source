import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HighlightComponentComponent } from './highlight-component.component';

describe('HighlightComponentComponent', () => {
  let component: HighlightComponentComponent;
  let fixture: ComponentFixture<HighlightComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HighlightComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HighlightComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
