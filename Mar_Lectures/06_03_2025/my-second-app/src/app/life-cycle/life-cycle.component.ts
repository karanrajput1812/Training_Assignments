import { Component, DoCheck, OnChanges, OnDestroy, OnInit, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-life-cycle',
  imports: [],
  templateUrl: './life-cycle.component.html',
  styleUrl: './life-cycle.component.css'
})
export class LifeCycleComponent implements OnInit, OnChanges, DoCheck, OnDestroy{
  constructor() {
    console.log("constructor() called.....");
  }
  ngOnDestroy(): void {
    console.log("ngOnDestroy() called.....");
  }
  ngDoCheck(): void {
    console.log("ngDoCheck() called.....");
  }
  ngOnChanges(changes: SimpleChanges): void {
    console.log("ngOnChanges() called.....");
  }
  ngOnInit() {
    console.log("ngOnInit() called.....");
  }
}
