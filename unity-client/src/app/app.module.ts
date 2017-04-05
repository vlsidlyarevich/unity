import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { ReactiveFormsModule } from "@angular/forms";
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
import { HeaderModule } from "./header/header.module";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { MyProfileModule } from "./my-profile/my-profile.module";
import { GitService } from "./services/GitService";
import { ProfileService } from "./services/ProfileService";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    NgbModule.forRoot(),
    BrowserModule,
    ReactiveFormsModule,
    HttpModule,
    HomeModule,
    AuthModule,
    SignupModule,
    HeaderModule,
    RouterModule,
    MyProfileModule,
    AppRoutingModule,
  ],
  providers: [
    AuthGuard,
    AuthenticationService,
    SignupService,
    GitService,
    ProfileService
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule {
}
