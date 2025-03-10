import { Component } from '@angular/core';

@Component({
  selector: 'app-event-driven',
  imports: [],
  templateUrl: './event-driven.component.html',
  styleUrl: './event-driven.component.css'
})
export class EventDrivenComponent {
  abc(obj: any) {
    obj.preventDefault();
    console.log("Event Driven Object")
    console.log(obj.target.elements[0].value);
  }
}
