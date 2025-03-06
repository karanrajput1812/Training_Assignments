import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';

@Component({
  selector: 'circle',
  imports: [],
  templateUrl: './circle.component.html',
  styleUrl: './circle.component.css'
})
export class CircleComponent implements OnChanges{
  diameter: number = 0;
  perimeter: number = 0;
  area: number = 0;
  @Input({alias:'radius',required:true})
  radius: number = 0;
  

  ngOnChanges(changes: SimpleChanges): void {
    this.diameter = 2*this.radius;
    this.area = 3.24*this.radius*this.radius;
    this.perimeter= 2*3.14*this.radius;
  }
}
