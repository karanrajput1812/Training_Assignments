import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { DepositCalculatorComponent } from "./deposit-calculator/deposit-calculator.component";
import { LoanCalculatorComponent } from './loan-calculator/loan-calculator.component';
import { MutualFundCalculatorComponent } from './mutual-fund-calculator/mutual-fund-calculator.component';

@Component({
  selector: 'app-calculator',
  imports: [CommonModule, DepositCalculatorComponent, LoanCalculatorComponent, MutualFundCalculatorComponent],
  templateUrl: './calculator.component.html',
  styleUrl: './calculator.component.css'
})
export class CalculatorComponent {
  type: string = "loan";
  changeCalculator(calType: string): void {
    this.type = calType;
    console.log(calType);
  }
}
