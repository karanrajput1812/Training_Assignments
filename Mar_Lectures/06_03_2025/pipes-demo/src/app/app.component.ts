import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { InbuiltPipesComponent } from "./inbuilt-pipes/inbuilt-pipes.component";
import { CustomPipesComponent } from "./custom-pipes/custom-pipes.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, InbuiltPipesComponent, CustomPipesComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'pipes-demo';
}
