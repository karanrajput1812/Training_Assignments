import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AttributeDirectiveComponent } from "./attribute-directive/attribute-directive.component";
import { StructuralDirectiveComponent } from './structural-directive/structural-directive.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, AttributeDirectiveComponent,StructuralDirectiveComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'directives-demo';
}
