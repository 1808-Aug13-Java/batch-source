import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HereoDetailComponent } from './hereo-detail.component';

describe('HereoDetailComponent', () => {
  let component: HereoDetailComponent;
  let fixture: ComponentFixture<HereoDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HereoDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HereoDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
