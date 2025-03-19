import { Component } from '@angular/core';
import { NavigationComponent } from "../../../navigation/navigation.component";
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-deposit-calculator',
  standalone: true,
  imports: [NavigationComponent, FormsModule, RouterLink],
  templateUrl: './deposit-calculator.component.html',
  styleUrls: ['./deposit-calculator.component.css']
})
export class DepositCalculatorComponent {
  amount: number = 0;
  tenure: number = 0;
  interest: number = 7;
  maturity: number = 0;
  error: String = "";
  calculateMaturity(depositAmount: number, depositInterest: number, depositTenure: number): number {
    const maturity = depositAmount * Math.pow(1 + depositInterest / 100, depositTenure);
    if (this.tenure > 7) {
      this.error = "Tenure should be less than 7 years";
  }
    return maturity;
  }

  onSubmit() {
    if (this.tenure > 7) {
      this.error = "Tenure should be less than 7 years";
  } else {
    this.maturity = this.calculateMaturity(this.amount, this.interest, this.tenure);
  }
}
}
