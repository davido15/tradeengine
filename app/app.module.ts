import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomepageComponent } from './homepage/homepage.component';
import { LoginpageComponent } from './loginpage/loginpage.component';
import { RegisterpageComponent } from './registerpage/registerpage.component';
import { DashpageComponent } from './dashpage/dashpage.component';


import { Routes, RouterModule } from '@angular/router';
import { PlaceorderComponent } from './placeorder/placeorder.component';
import { ListorderComponent } from './listorder/listorder.component';
import { CancelorderComponent } from './cancelorder/cancelorder.component';




const routes: Routes = [
  {path:  "", pathMatch:  "full",redirectTo:  "/home"},
  {path: "home", component: HomepageComponent},
  {path: "login", component: LoginpageComponent},
  {path: "register", component: RegisterpageComponent},
  {path: "dashboard", component: DashpageComponent},
  {path: "list", component: ListorderComponent},
  {path: "cancel", component: CancelorderComponent},
  {path: "create", component: PlaceorderComponent},
    
];

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    LoginpageComponent,
    RegisterpageComponent,
    DashpageComponent,
    PlaceorderComponent,
    ListorderComponent,
    CancelorderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(routes)  
  ],
  exports: [RouterModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
