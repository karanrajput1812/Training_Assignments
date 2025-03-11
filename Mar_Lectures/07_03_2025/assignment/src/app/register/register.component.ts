import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, ValidatorFn, AbstractControl, ValidationErrors, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SessionService } from '../service/session.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule, CommonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit {
  employeeForm!: FormGroup;

  constructor(public cs: SessionService) {}

  ngOnInit() {
    this.employeeForm = new FormGroup({
      cid: new FormControl('', [
        Validators.required,
        Validators.pattern(/^\d{7}$/) // Exactly 7 digits
      ]),
      uname: new FormControl('', [
        Validators.required,
        Validators.minLength(5),
        this.noDigitValidator()
      ]),
      password: new FormControl('', [
        Validators.required,
        Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\W).{8,}$/)
      ]),
      cpassword: new FormControl('', Validators.required),
      accountNo: new FormControl('', [
        Validators.required,
        Validators.minLength(6),
        Validators.pattern(/^\d+$/)
      ])
    }),{ validators: this.passwordMatchValidator() }; // Attach custom group-level validator
  }

  noDigitValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const hasDigit = /\d/.test(control.value);
      return hasDigit ? { hasDigit: true } : null;
    };
  }

  passwordMatchValidator(): ValidatorFn {
    return (group: AbstractControl): ValidationErrors | null => {
      const password = group.get('password')?.value;
      const cpassword = group.get('cpassword')?.value;
      return password === cpassword ? null : { passwordMismatch: true };
    };
  }

  abc(obj: any) {
    if (this.employeeForm.valid) {
      this.cs.addUser(obj);
    }
  }
}
