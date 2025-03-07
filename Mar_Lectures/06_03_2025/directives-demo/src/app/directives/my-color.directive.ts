import { Directive, ElementRef } from '@angular/core';

@Directive({
  selector: '[myColor]'
})
export class MyColorDirective {

  constructor(private element: ElementRef) { 
    
  }
  ngOnInit(): void {
    this.element.nativeElement.style.color = "Red";
  }

}
