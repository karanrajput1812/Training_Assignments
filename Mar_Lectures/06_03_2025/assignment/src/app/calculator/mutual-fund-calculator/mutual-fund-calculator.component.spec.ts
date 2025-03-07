import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MutualFundCalculatorComponent } from './mutual-fund-calculator.component';

describe('MutualFundCalculatorComponent', () => {
  let component: MutualFundCalculatorComponent;
  let fixture: ComponentFixture<MutualFundCalculatorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MutualFundCalculatorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MutualFundCalculatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
