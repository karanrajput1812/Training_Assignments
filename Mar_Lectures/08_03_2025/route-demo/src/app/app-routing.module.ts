import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { ServicesComponent } from './services/services.component';
import { ContactUsComponent } from './contact-us/contact-us.component';
import { LoginComponent } from './login/login.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { welcomeGuard } from './guards/welcome.guard';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: "about",
    component: AboutUsComponent
  },
  {
    path: "service",
    component: ServicesComponent
  },
  {
    path: "contact",
    component: ContactUsComponent,
  },
  {
    path: "netbanking",
    component: LoginComponent
  },
  {
    path: "welcome",
    component: WelcomeComponent,
    canActivate: [welcomeGuard],
    data: ['MANAGER']
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
