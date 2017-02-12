import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { HttpModule } from "@angular/http";
import { AppComponent } from "./app.component";
import { HomeModule } from "./home/home.module";
import { AuthModule } from "./auth/auth.module";
import { SignupModule } from "./signup/signup.module";
import { RouterModule } from "@angular/router";
import { AppRoutingModule } from "./app.routes";
import { AuthGuard } from "./guards/auth.guard";
import { AuthenticationService } from "./services/AuthenticationService";
import { SignupService } from "./services/SignupService";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    HomeModule,
    AuthModule,
    SignupModule,
    RouterModule,
    AppRoutingModule
  ],
  providers: [
    AuthGuard,
    AuthenticationService,
    SignupService
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule {
}
