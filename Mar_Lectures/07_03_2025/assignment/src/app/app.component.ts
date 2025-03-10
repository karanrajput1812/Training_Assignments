import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoginComponent } from "./login/login.component";
import { RegisterComponent } from "./register/register.component";
import { SessionService } from './service/session.service';
import { HomeComponent } from "./home/home.component";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RegisterComponent, HomeComponent, LoginComponent, CommonModule  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'assignment';

  // type: string = "loan";
  getLogin(): void {
    this.cs.login_register = false;
  }

  getRegister(): void {
    this.cs.login_register = true;
  }

  constructor(public cs: SessionService) {

  }
  changeForm(): void {
    this.cs.login_register = !this.cs.login_register;
  }
}
