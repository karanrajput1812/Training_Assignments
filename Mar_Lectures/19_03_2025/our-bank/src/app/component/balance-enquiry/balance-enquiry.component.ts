import { Component } from '@angular/core';
import { NavigationComponent } from "../navigation/navigation.component";
import { HttpClient } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { authReducer } from '../../states/auth.reducer';
import { Store } from '@ngrx/store';
import { AppState } from '../../states/app.state';
import { selectAuth } from '../../states/auth.selector';
@Component({
  selector: 'app-balance-enquiry',
  imports: [NavigationComponent, HttpClientModule],
  templateUrl: './balance-enquiry.component.html',
  styleUrl: './balance-enquiry.component.css'
})
export class BalanceEnquiryComponent {
  user: any = null;
  balance: number = 0;
  state: any;

  constructor(private http: HttpClient, private store: Store<AppState>) {
    this.state = this.store.select(selectAuth);
    
  }

  ngOnInit(): void {
    // Simulate fetching user data (replace with actual service call if needed)
    this.user = { id: 3 }; // Example user object
    console.log(this.state);
    if (this.user) {
      this.http.get<number>(`http://localhost:8080/checkBalance/${this.user.id}`)
        .subscribe({
          next: (data: number) => {
            this.balance = data;
            console.log(data);
          },
          error: (err: any) => {
          }
        });
    }
  }
}
