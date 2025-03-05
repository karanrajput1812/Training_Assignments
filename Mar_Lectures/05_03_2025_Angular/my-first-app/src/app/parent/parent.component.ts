import { Component } from '@angular/core';
import { ChildComponent } from './child/child.component';
import { SiblingComponent } from './sibling/sibling.component';

@Component({
  selector: 'parent',
  imports: [ChildComponent, SiblingComponent],
  templateUrl: './parent.component.html',
  styleUrl: './parent.component.css'
})
export class ParentComponent {

}
