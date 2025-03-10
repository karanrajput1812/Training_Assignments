import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule, Validators, FormControl, FormGroup } from '@angular/forms';
import { SessionService } from '../service/session.service';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  employeeForm: any;
  
    constructor(public cs : SessionService) {

    }
  
    ngOnInit() {
      this.employeeForm = new FormGroup({
            cid: new FormControl("",Validators.required),
            password: new FormControl("", Validators.required)
          })
    }  
    abc(obj: any) {
      this.cs.validateUser(obj);
    }
    
}
