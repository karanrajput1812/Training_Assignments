import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-loan-calculator',
  imports: [FormsModule],
  templateUrl: './loan-calculator.component.html',
  styleUrl: './loan-calculator.component.css'
})
export class LoanCalculatorComponent {
  amount: number = 0;
  tenure: number = 0;
  interest: number = 0;
  emi: number = 0;
  type: string = "";
  return: number = 0;
  error: string = "";
  updatedInterest(): void {
    if (this.type === 'home') {
      this.interest = 9;
    } else if (this.type === 'car') {
      this.interest = 11;
    } else if (this.type === 'personal') {
      this.interest = 15;
    }
  }
  calculate(sip: string, duration: string, roi: string): void {
    console.log(sip);
    const monthlyRate = parseInt(roi) / 12 / 100;
    const months = parseInt(duration) * 12;
    this.emi = (this.amount * monthlyRate * Math.pow(1 + monthlyRate, months)) / (Math.pow(1 + monthlyRate, months) - 1);
    console.log(this.return);
  }
}
