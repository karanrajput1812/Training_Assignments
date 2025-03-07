import { Directive, HostListener } from '@angular/core';

@Directive({
  selector: '[myEvent]'
})
export class MyEventDirective {

  constructor() { }

  @HostListener('click')
  abc() {
    console.log("Button Clicked");
  }


  @HostListener('mouseenter')
  xyz() {
    console.log("Mouse Enter")
  }

  @HostListener('mouseleave')
  atoz() {
    console.log("Mouse Leave")
  }


}
