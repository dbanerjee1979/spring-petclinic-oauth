import { TestBed } from '@angular/core/testing';

import { VetsService } from './vets.service';

describe('VetsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: VetsService = TestBed.get(VetsService);
    expect(service).toBeTruthy();
  });
});
