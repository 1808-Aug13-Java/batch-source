import { TestBed } from '@angular/core/testing';

import { PopulateService } from './populate.service';

describe('PopulateService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PopulateService = TestBed.get(PopulateService);
    expect(service).toBeTruthy();
  });
});
