import { RouterModule, Routes } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { NotFoundPageComponent } from './pages/not-found-page/not-found-page.component';
import { SocialLoginPageComponent } from './pages/social-login-page/social-login-page.component';
import { ProfilePageComponent } from './pages/profile-page/profile-page.component';
import { UsersPageComponent } from "./pages/users-page/users-page.component";
import { UserEditPageComponent } from "./pages/user-edit-page/user-edit-page.component";
import { UserAddPageComponent } from "./pages/user-add-page/user-add-page.component";
import { AnalyticsResultsPageComponent } from "./pages/analytics-results-page/analytics-results-page.component";
import { AnalyticsNewGithubPageComponent } from "./pages/analytics-new-github-page/analytics-new-github-page.component";
import { AnalyticsNewLinkedinPageComponent } from "./pages/analytics-new-linkedin-page/analytics-new-linkedin-page.component";
import { AnalyticsNewTwitterPageComponent } from "./pages/analytics-new-twitter-page/analytics-new-twitter-page.component";
import { AnalyticsReportPageComponent } from "./pages/analytics-report-page/analytics-report-page.component";

const appRoutes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'login', component: LoginPageComponent },
  { path: 'profile', component: ProfilePageComponent },
  { path: 'users', component: UsersPageComponent },
  { path: 'users/new', component: UserAddPageComponent },
  { path: 'users/:id', component: UserEditPageComponent },
  { path: 'analytics', component: AnalyticsResultsPageComponent },
  { path: 'analytics/report/:id', component: AnalyticsReportPageComponent },
  { path: 'analytics/new/github', component: AnalyticsNewGithubPageComponent },
  { path: 'analytics/new/twitter', component: AnalyticsNewTwitterPageComponent },
  { path: 'analytics/new/linkedin', component: AnalyticsNewLinkedinPageComponent },
  { path: 'social-auth', component: SocialLoginPageComponent },
  { path: '404', component: NotFoundPageComponent },
  { path: '**', redirectTo: '/404' }
];

export const Routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
