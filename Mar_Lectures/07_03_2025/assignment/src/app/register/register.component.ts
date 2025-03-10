import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { SessionService } from '../service/session.service';


@Component({
  selector: 'app-register',
  imports: [ReactiveFormsModule, FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  employeeForm: any;

  constructor(public cs : SessionService) {
  
  }
  

  ngOnInit() {
    this.employeeForm = new FormGroup({
      cid: new FormControl("", Validators.compose([
      Validators.required,
      this.cidValidator
      ])),
      uname: new FormControl("", Validators.compose([
      Validators.required,
      Validators.minLength(5),
      this.noDigitValidator
      ])),
      password: new FormControl("", Validators.compose([
      Validators.required,
      Validators.minLength(8),
      Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\W).*$/)
      ])),
      cpassword: new FormControl("", Validators.compose([
      Validators.required,
      // this.passwordMatchValidator.bind(this)
      ])),
      accountNo: new FormControl("", Validators.compose([
      Validators.required,
      Validators.minLength(6),
      Validators.pattern(/^\d+$/)
      ]))
    });
  }
  ciderror: string = "";
  cidValidator(control: any):any {
    if(control.value.length===7)
      return {user: true};
  }

  noDigitValidator(control: FormControl): { [key: string]: boolean } | null {
    const hasDigit = /\d/.test(control.value);
    return hasDigit ? { 'hasDigit': true } : null;
  }

  passwordMatchValidator(control: FormControl): { [key: string]: boolean } | null {
    if (this.employeeForm && control.value == this.employeeForm.get('password').value) {
      return { 'passwordMismatch': true };
    }
    return null;
  }

  abc(obj: any) {
    this.cs.addUser(obj);
  }
}