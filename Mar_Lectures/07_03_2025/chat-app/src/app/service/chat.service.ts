import { Injectable } from '@angular/core';
import { LogService } from './log.service';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  public messages: string[] = [];

  constructor(private ls: LogService) { 
    console.log("ChatService object created......");
  }

  chatMessage(msg: string) {
    this.messages.push(msg);
    this.ls.logMessage(msg);
  }
}
