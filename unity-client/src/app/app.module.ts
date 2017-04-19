import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { ReactiveFormsModule } from "@angular/forms";
import { HttpModule } from "@angular/http";
import { AppComponent } from "./app.component";
import { HomeModule } from "./components/home/home.module";
import { AuthModule } from "./components/auth/auth.module";
import { SignupModule } from "./components/signup/signup.module";
import { RouterModule } from "@angular/router";
import { AppRoutingModule } from "./app.routes";
import { AuthGuard } from "./guards/auth.guard";
import { AuthenticationService } from "./services/AuthenticationService";
import { SignupService } from "./services/SignupService";
import { HeaderModule } from "./components/header/header.module";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { MyProfileModule } from "./components/my-profile/my-profile.module";
import { GitService } from "./services/GitService";
import { UserSocialService } from "./services/UserSocialService";
import { UserService } from "./services/UserService";
import { BootstrapModalModule } from "ng2-bootstrap-modal";
import { GitAnalyzeModule } from "./components/git-analyze/git-analyze.module";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    NgbModule.forRoot(),
    BootstrapModalModule,
    BrowserModule,
    ReactiveFormsModule,
    HttpModule,
    HomeModule,
    AuthModule,
    SignupModule,
    HeaderModule,
    RouterModule,
    MyProfileModule,
    GitAnalyzeModule,
    AppRoutingModule
  ],
  providers: [
    AuthGuard,
    AuthenticationService,
    SignupService,
    GitService,
    UserSocialService,
    UserService
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule {
}
