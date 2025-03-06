import { Component } from '@angular/core';
import { NavigationComponent } from "../../navigation/navigation.component";
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-calculators',
  imports: [NavigationComponent, RouterLink],
  templateUrl: './calculators.component.html',
  styleUrl: './calculators.component.css'
})
export class CalculatorsComponent {

}
