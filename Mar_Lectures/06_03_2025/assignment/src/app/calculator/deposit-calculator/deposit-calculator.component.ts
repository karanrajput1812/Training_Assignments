import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-deposit-calculator',
  imports: [FormsModule, CommonModule],
  templateUrl: './deposit-calculator.component.html',
  styleUrl: './deposit-calculator.component.css'
})
export class DepositCalculatorComponent {
  return: number = 0;
  error: string = ""
  calculate(amount: string, tenure: string, interest: string): void {
    const tenureValue = parseFloat(tenure);
    if (tenureValue <= 0 || tenureValue >= 10) {
      this.error = "Tenure must be less than 10 years";
      this.return = 0;
      return;
    } else {
      this.error = "";
    }
   this.return = parseFloat(amount) * Math.pow(1 + parseFloat(interest) / 100, parseFloat(tenure));
}
}
