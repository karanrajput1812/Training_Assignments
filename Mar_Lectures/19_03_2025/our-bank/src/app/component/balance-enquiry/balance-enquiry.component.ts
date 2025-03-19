import { Component } from '@angular/core';
import { NavigationComponent } from "../navigation/navigation.component";
import { HttpClient } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
@Component({
  selector: 'app-balance-enquiry',
  imports: [NavigationComponent, HttpClientModule],
  templateUrl: './balance-enquiry.component.html',
  styleUrl: './balance-enquiry.component.css'
})
export class BalanceEnquiryComponent {
  user: any = null;
  balance: number = 0;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    // Simulate fetching user data (replace with actual service call if needed)
    this.user = { id: 1 }; // Example user object

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
