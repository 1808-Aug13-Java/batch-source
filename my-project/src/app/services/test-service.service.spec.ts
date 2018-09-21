import { TestBed } from '@angular/core/testing';

import { TestServiceService } from './test-service.service';

describe('TestServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TestServiceService = TestBed.get(TestServiceService);
    expect(service).toBeTruthy();
  });
});
