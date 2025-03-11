import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DemoService {

  constructor() { }

  doSomeTask() {
    console.log("Did Some task.....")
  }
}
