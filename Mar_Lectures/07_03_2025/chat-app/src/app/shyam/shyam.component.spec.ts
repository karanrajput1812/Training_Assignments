import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShyamComponent } from './shyam.component';

describe('ShyamComponent', () => {
  let component: ShyamComponent;
  let fixture: ComponentFixture<ShyamComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShyamComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShyamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
