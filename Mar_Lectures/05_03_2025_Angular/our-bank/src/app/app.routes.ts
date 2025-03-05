import { Routes } from '@angular/router';
import { HomeComponent } from './container/home/home.component';
import { AboutUsComponent } from './container/about-us/about-us.component';
// import { ServicesComponent } from './services/services.component';
// import { NetBankingComponent } from './net-banking/net-banking.component';
// import { ContactUsComponent } from './contact-us/contact-us.component';

export const routes: Routes = [
    { path: 'home', component: HomeComponent },
    { path: 'about-us', component: AboutUsComponent },
    // { path: 'services', component: ServicesComponent },
    // { path: 'net-banking', component: NetBankingComponent },
    // { path: 'contact-us', component: ContactUsComponent },
    { path: '', redirectTo: '/home', pathMatch: 'full' }
];
