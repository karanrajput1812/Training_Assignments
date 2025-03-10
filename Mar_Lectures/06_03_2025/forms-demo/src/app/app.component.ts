import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TemplateDrivenComponent } from './template-driven/template-driven.component';
import { ReactiveFormsComponent } from './reactive-forms/reactive-forms.component';
import { EventDrivenComponent } from './event-driven/event-driven.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, TemplateDrivenComponent, ReactiveFormsComponent, EventDrivenComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'forms-demo';
}
