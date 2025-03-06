import { Component } from '@angular/core';
import { SquarePipe } from "../pipes/square.pipe";
import { PowerPipe } from "../pipes/power.pipe";

@Component({
  selector: 'app-custom-pipes',
  imports: [SquarePipe, PowerPipe],
  templateUrl: './custom-pipes.component.html',
  styleUrl: './custom-pipes.component.css'
})
export class CustomPipesComponent {

}
