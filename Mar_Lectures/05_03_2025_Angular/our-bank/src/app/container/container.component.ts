import { Component } from '@angular/core';
import { HomeComponent } from "./home/home.component";
@Component({
  selector: 'app-container',
  imports: [ HomeComponent],
  templateUrl: './container.component.html',
  styleUrl: './container.component.css'
})
export class ContainerComponent {

}
