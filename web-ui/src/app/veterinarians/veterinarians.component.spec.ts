import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VeterinariansComponent } from './veterinarians.component';

describe('VeterinariansComponent', () => {
  let component: VeterinariansComponent;
  let fixture: ComponentFixture<VeterinariansComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VeterinariansComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VeterinariansComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
