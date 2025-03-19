import { Component } from '@angular/core';
import { NavigationComponent } from "../navigation/navigation.component";
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-services',
  imports: [NavigationComponent,RouterLink],
  templateUrl: './services.component.html',
  styleUrl: './services.component.css'
})
export class ServicesComponent {

}
