import { Component } from '@angular/core';
import { MyColorDirective } from '../directives/my-color.directive';
import { MyEventDirective } from '../directives/my-event.directive';

@Component({
  selector: 'app-custom-directive',
  imports: [MyColorDirective, MyEventDirective],
  templateUrl: './custom-directive.component.html',
  styleUrl: './custom-directive.component.css'
})
export class CustomDirectiveComponent {
  
}
