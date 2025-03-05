import { Component } from '@angular/core';

@Component({
  selector: 'app-property-binding',
  imports: [],
  templateUrl: './property-binding.component.html',
  styleUrl: './property-binding.component.css'
})
export class PropertyBindingComponent {
  name : String = "Ganesh";
  married : boolean = true;
  uiType : String = "text";
  greet() : String {
    return "Good Morning Everybody";
  }
}
