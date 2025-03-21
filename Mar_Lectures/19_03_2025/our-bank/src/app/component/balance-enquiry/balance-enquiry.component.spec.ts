import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BalanceEnquiryComponent } from './balance-enquiry.component';

describe('BalanceEnquiryComponent', () => {
  let component: BalanceEnquiryComponent;
  let fixture: ComponentFixture<BalanceEnquiryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BalanceEnquiryComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BalanceEnquiryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
