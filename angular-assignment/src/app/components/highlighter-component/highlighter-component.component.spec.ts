import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HighlighterComponentComponent } from './highlighter-component.component';

describe('HighlighterComponentComponent', () => {
  let component: HighlighterComponentComponent;
  let fixture: ComponentFixture<HighlighterComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HighlighterComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HighlighterComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
