import { Component, Input } from '@angular/core';

@Component({
  selector: 'employee',
  imports: [],
  templateUrl: './employee.component.html',
  styleUrl: './employee.component.css'
})
export class EmployeeComponent {

  @Input({alias:'user',required:true})
  name: String = "Guest";
  @Input('age')     // accesible using "age" in employee tag wherever used
  age: number = 21;

  // changeMe() :void{
  //   this.age++;
  //   this.name = "Changed";
  // }
}