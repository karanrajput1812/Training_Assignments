import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient} from '@angular/common/http';
import { NavigationComponent } from '../navigation/navigation.component';
import { CommonModule } from '@angular/common';
// Removed unused imports

@Component({
  selector: 'app-netbanking',
  imports: [NavigationComponent, FormsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './netbanking.component.html',
  styleUrls: ['./netbanking.component.css']
})
export class NetbankingComponent {
  msg: string = '';
  loginForm: FormGroup;

  constructor(private fb: FormBuilder, private router: Router, private http: HttpClient) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  loginUser(): void {
    if (this.loginForm.invalid) {
      this.msg = 'Please fill in all required fields.';
      return;
    }

    const user = this.loginForm.value;

    this.http.post('http://localhost:8080/login', user).subscribe({
      next: (res: any) => {
        if (!res.email) {
          console.log(res);
          this.msg = 'Incorrect Username Or Password';
        } else {
          console.log(res);
          this.router.navigate(['/user/balance-enquiry']);
        }
      },
      error: (err) => {
        console.error(err);
        this.msg = 'Server Error';
      }
    });
  }
}
