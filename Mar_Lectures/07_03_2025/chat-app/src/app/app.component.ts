import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { RamComponent } from "./ram/ram.component";
import { ShyamComponent } from "./shyam/shyam.component";
import { GhanshyamComponent } from "./ghanshyam/ghanshyam.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RamComponent, ShyamComponent, GhanshyamComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'chat-app';
}
