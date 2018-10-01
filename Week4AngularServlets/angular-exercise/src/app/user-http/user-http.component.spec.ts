import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserHttpComponent } from './user-http.component';

describe('UserHttpComponent', () => {
  let component: UserHttpComponent;
  let fixture: ComponentFixture<UserHttpComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserHttpComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserHttpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
