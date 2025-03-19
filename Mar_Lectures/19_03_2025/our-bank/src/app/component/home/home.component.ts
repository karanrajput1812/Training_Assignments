import { Component } from '@angular/core';
import { NavigationComponent } from "../navigation/navigation.component";
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [NavigationComponent,RouterLink],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
