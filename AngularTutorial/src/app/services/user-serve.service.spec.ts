import { TestBed } from '@angular/core/testing';

import { UserServeService } from './user-serve.service';

describe('UserServeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UserServeService = TestBed.get(UserServeService);
    expect(service).toBeTruthy();
  });
});
