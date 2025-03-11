import { Component } from '@angular/core';
import { DemoService } from '../demo.service';

@Component({
  selector: 'app-demo-comp',
  standalone: false,
  templateUrl: './demo-comp.component.html',
  styleUrl: './demo-comp.component.css'
})
export class DemoCompComponent {
  constructor(private ds: DemoService) {}

  ngOnInit(): void {
    // this.ds.doSomeTask();
  }

  method() {
    this.ds.doSomeTask();
  }

}
