import { Component } from '@angular/core';

@Component({
  selector: 'app-interpolation',
  imports: [],
  templateUrl: './interpolation.component.html',
  styleUrl: './interpolation.component.css'
})
export class InterpolationComponent {
  name: String = "Ganesh"
  age: Number = 33;
  getName(): String {
    return this.name;
  }
  getAge(): Number {
    return this.age;
  }

}
