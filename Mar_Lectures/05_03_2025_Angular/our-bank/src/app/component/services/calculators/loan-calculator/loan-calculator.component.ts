import { Component } from '@angular/core';
import { NavigationComponent } from '../../../navigation/navigation.component';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-loan-calculator',
  standalone: true,
  imports: [NavigationComponent, FormsModule,RouterLink],
  templateUrl: './loan-calculator.component.html',
  styleUrls: ['./loan-calculator.component.css'],
})
export class LoanCalculatorComponent {
  amount: number = 0;
  tenure: number = 0;
  interest: number = 0;
  emi: number = 0;
  type: string = "";

  updatedInterest(): void {
    if (this.type === 'home') {
      this.interest = 9;
    } else if (this.type === 'car') {
      this.interest = 11;
    } else if (this.type === 'personal') {
      this.interest = 15;
    }
  }

  onSubmit(): void {
    const monthlyInterestRate = this.interest / 1200;
    const numberOfMonths = this.tenure * 12;
    this.emi = (this.amount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfMonths)) / (Math.pow(1 + monthlyInterestRate, numberOfMonths) - 1);
  }

  
}
