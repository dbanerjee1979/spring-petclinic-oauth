import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VisitEditComponent } from './visit-edit.component';

describe('VisitEditComponent', () => {
  let component: VisitEditComponent;
  let fixture: ComponentFixture<VisitEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VisitEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VisitEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
