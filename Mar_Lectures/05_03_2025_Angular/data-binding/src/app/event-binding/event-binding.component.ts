import { Component } from '@angular/core';

@Component({
  selector: 'app-event-binding',
  imports: [],
  templateUrl: './event-binding.component.html',
  styleUrl: './event-binding.component.css'
})
export class EventBindingComponent {

  display():void {
    console.log("WELCOME TO EVENT BINDING");
  }

  printMessage(msg: String) : void {
    console.log(msg);
  }
}
