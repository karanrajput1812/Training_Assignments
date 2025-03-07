import { Component } from '@angular/core';
import { ChatService } from '../service/chat.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-shyam',
  imports: [CommonModule],
  templateUrl: './shyam.component.html',
  styleUrl: './shyam.component.css'
})
export class ShyamComponent {
  name: string = "Shyam";

  constructor(public cs: ChatService) {

  }
  sendMessage(msg: string) {
    this.cs.chatMessage(this.name + " : " + msg);
  }
}
