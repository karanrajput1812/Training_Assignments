import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ParentComponent } from './parent/parent.component';
import { MyCompComponent } from './my-comp/my-comp.component';

@Component({
  selector: 'app',
  imports: [ParentComponent, MyCompComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  greet: String = "Good Morning Everybody";

  setGreet() : String
  {
    return this.greet = "GOOD AFTERNOON";
  }
}
