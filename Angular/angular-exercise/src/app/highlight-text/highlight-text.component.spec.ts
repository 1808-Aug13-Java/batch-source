import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HighlightTextComponent } from './highlight-text.component';

describe('HighlightTextComponent', () => {
  let component: HighlightTextComponent;
  let fixture: ComponentFixture<HighlightTextComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HighlightTextComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HighlightTextComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
