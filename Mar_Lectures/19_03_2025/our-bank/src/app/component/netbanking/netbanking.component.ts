import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient} from '@angular/common/http';
import { NavigationComponent } from '../navigation/navigation.component';
import { AsyncPipe, CommonModule } from '@angular/common';
import { Observable } from 'rxjs';
import { Store } from '@ngrx/store';
import { AppState } from '../../states/app.state';
import { selectAuth } from '../../states/auth.selector';
// Removed unused imports

@Component({
  selector: 'app-netbanking',
  imports: [NavigationComponent, FormsModule, ReactiveFormsModule, CommonModule,AsyncPipe],
  templateUrl: './netbanking.component.html',
  styleUrls: ['./netbanking.component.css']
})
export class NetbankingComponent {
  auth: Observable<boolean>;

  msg: string = '';
  loginForm: FormGroup;

  constructor(private fb: FormBuilder, private router: Router, private http: HttpClient, private store: Store<AppState>) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
    this.auth = this.store.select(selectAuth);
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
