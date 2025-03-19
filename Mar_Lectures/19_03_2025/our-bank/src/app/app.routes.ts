import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { HomeComponent } from './component/home/home.component';
import { AboutUsComponent } from './component/about-us/about-us.component';
import { ServicesComponent } from './component/services/services.component';
import { NetbankingComponent } from './component/netbanking/netbanking.component';
import { RegisterComponent } from './component/register/register.component';
import { ContactUsComponent } from './component/contact-us/contact-us.component';
import { CalculatorsComponent } from './component/services/calculators/calculators.component';
import { LoanCalculatorComponent } from './component/services/calculators/loan-calculator/loan-calculator.component';
import { DepositCalculatorComponent } from './component/services/calculators/deposit-calculator/deposit-calculator.component';
import { BalanceEnquiryComponent } from './component/balance-enquiry/balance-enquiry.component';

export const routes: Routes = [
    { path: 'home', component: HomeComponent },
    { path: 'about-us', component: AboutUsComponent },
    { path: 'services', component: ServicesComponent },
    { path: 'net-banking', component: NetbankingComponent },
    { path: 'register', component: RegisterComponent},
    { path: 'contact-us', component: ContactUsComponent },
    { path: 'services/calculator', component: CalculatorsComponent },
    { path: 'services/calculator/loan-calculator', component: LoanCalculatorComponent},
    { path: 'services/calculator/deposit-calculator', component: DepositCalculatorComponent},
    { path: 'user/balance-enquiry', component:BalanceEnquiryComponent},
    { path: '', redirectTo: '/home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }