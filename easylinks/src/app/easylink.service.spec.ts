import { TestBed } from '@angular/core/testing';

import { EasylinkService } from './easylink.service';

describe('EasylinkService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EasylinkService = TestBed.get(EasylinkService);
    expect(service).toBeTruthy();
  });
});
