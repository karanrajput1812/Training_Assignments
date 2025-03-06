import { Component } from '@angular/core';
import { NavigationComponent } from "../navigation/navigation.component";
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-netbanking',
  imports: [NavigationComponent, RouterLink],
  templateUrl: './netbanking.component.html',
  styleUrl: './netbanking.component.css'
})
export class NetbankingComponent {

}
