import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { TopnavbarComponent } from './components/menu/topnavbar/topnavbar.component';
import { SidebarComponent } from './components/menu/sidebar/sidebar.component';
import { FooterComponent } from './components/footer/footer.component';
import { NotFoundPageComponent } from './pages/not-found-page/not-found-page.component';
import { AccessDeniedPageComponent } from './pages/access-denied-page/access-denied-page.component';
import { InternalServerErrorPageComponent } from './pages/internal-server-error-page/internal-server-error-page.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { BrowserModule } from '@angular/platform-browser';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { Routing } from './app.routing';
import { LoginFormComponent } from './components/login/login-form/login-form.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AuthenticationService } from './services/authentication.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SocialLoginPageComponent } from './pages/social-login-page/social-login-page.component';
import { Ng2Webstorage } from 'ng2-webstorage';
import { CookieService } from 'ngx-cookie-service';
import { ProfilePageComponent } from './pages/profile-page/profile-page.component';
import { ProfileComponent } from './components/profile/profile.component';
import { ProfileService } from './services/profile.service';
import { ImageUploadModule } from 'angular2-image-upload';
import { NotificationService } from './services/notification.service';
import { NotificationComponent } from './components/notification/notification.component';
import { LoadingComponent } from './components/loading/loading.component';
import { LoaderService } from './services/loader.service';
import { PipeModule } from './pipes/pipe-module/pipe.module';
import { ImageService } from './services/image.service';
import { ProfileStoreService } from './services/store/profile-store.service';
import { AuthenticationTokenInterceptor } from './interceptors/authentication-token.interceptor';
import { TokenService } from "./services/token.service";
import { UsersPageComponent } from './pages/users-page/users-page.component';
import { UsersComponent } from './components/admin/users/users.component';
import { UserService } from "./services/user.service";
import { NgxPaginationModule } from "ngx-pagination";

@NgModule({
  declarations: [
    AppComponent,
    TopnavbarComponent,
    SidebarComponent,
    FooterComponent,
    LoginPageComponent,
    HomePageComponent,
    NotFoundPageComponent,
    AccessDeniedPageComponent,
    InternalServerErrorPageComponent,
    HomePageComponent,
    LoginFormComponent,
    SocialLoginPageComponent,
    ProfilePageComponent,
    ProfileComponent,
    NotificationComponent,
    LoadingComponent,
    UsersPageComponent,
    UsersComponent
  ],
  imports: [
    PipeModule.forRoot(),
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    Routing,
    Ng2Webstorage,
    ImageUploadModule.forRoot(),
    NgxPaginationModule
  ],
  providers: [AuthenticationService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthenticationTokenInterceptor,
      multi: true
    },
    TokenService,
    CookieService,
    ProfileStoreService,
    ProfileService,
    UserService,
    ImageService,
    NotificationService,
    LoaderService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
