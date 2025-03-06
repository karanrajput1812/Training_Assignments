import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CircleComponent } from "./circle/circle.component";
import { MoneyPipe } from "./pipe/money.pipe";
import { CalculatorComponent } from "./calculator/calculator.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, CircleComponent, MoneyPipe, CalculatorComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'assignment';
  radius : number = 0;
  currency : number = 1;
  setRadius(r: number): void {
    this.radius = r;
  } 
  setCurrency(c: number): void {
    this.currency = c;
  }

}
