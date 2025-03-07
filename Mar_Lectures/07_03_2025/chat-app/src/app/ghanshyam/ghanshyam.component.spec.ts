import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GhanshyamComponent } from './ghanshyam.component';

describe('GhanshyamComponent', () => {
  let component: GhanshyamComponent;
  let fixture: ComponentFixture<GhanshyamComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GhanshyamComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GhanshyamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
