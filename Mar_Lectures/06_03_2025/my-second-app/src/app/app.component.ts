import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { EmployeeComponent } from "./employee/employee.component";
import { LifeCycleComponent } from "./life-cycle/life-cycle.component";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [EmployeeComponent, LifeCycleComponent, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'my-second-app';

  flag:boolean = true;

  toggle() : void {
    this.flag = !this.flag;
  }
}
