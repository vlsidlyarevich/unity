import { RouterModule, Routes } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { NotFoundPageComponent } from './pages/not-found-page/not-found-page.component';
import { SocialLoginPageComponent } from './pages/social-login-page/social-login-page.component';
import { ProfilePageComponent } from './pages/profile-page/profile-page.component';

const appRoutes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'login', component: LoginPageComponent },
  { path: 'profile', component: ProfilePageComponent },
  { path: 'social-auth', component: SocialLoginPageComponent },
  { path: '404', component: NotFoundPageComponent },
  { path: '**', redirectTo: '/404' }
];

export const Routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
