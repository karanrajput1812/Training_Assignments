import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-calculator',
  imports: [CommonModule],
  templateUrl: './calculator.component.html',
  styleUrl: './calculator.component.css'
})
export class CalculatorComponent {
  type: string = "loan";
  changeCalculator(calType: string): void {
    this.type = calType;
    console.log(calType);
  }
  return: number = 0;

  calculate(sip: string, duration: string, roi: string): void {
    console.log(sip);
    const monthlyRate = parseInt(roi) / 12 / 100;
    const months = parseInt(duration) * 12;
    this.return = parseInt(sip) * ((Math.pow(1 + monthlyRate, months) - 1) / monthlyRate) * (1 + monthlyRate);
    console.log(this.return);
  }
}
