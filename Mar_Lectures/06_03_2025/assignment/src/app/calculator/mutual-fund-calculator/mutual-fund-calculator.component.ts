import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-mutual-fund-calculator',
  imports: [CommonModule],
  templateUrl: './mutual-fund-calculator.component.html',
  styleUrls: ['./mutual-fund-calculator.component.css']
})
export class MutualFundCalculatorComponent {
  return: number = 0;
  error: string = "";

  calculate(sip: string, duration: string, roi: string): void {
    const roiValue = parseFloat(roi);
    if (roiValue < -25 || roiValue > 25) {
      this.error = "ROI must be between -25% and 25%";
      this.return = 0;
      return;
    } else {
      this.error = "";
    }

    const monthlyRate = roiValue / 12 / 100;
    const months = parseInt(duration) * 12;
    this.return = parseInt(sip) * ((Math.pow(1 + monthlyRate, months) - 1) / monthlyRate) * (1 + monthlyRate);
  }
}
