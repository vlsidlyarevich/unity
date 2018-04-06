import { RouterModule, Routes } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { NotFoundPageComponent } from './pages/not-found-page/not-found-page.component';
import { ProfilePageComponent } from './pages/profile-page/profile-page.component';
import { UsersPageComponent } from "./pages/users-page/users-page.component";
import { UserEditPageComponent } from "./pages/user-edit-page/user-edit-page.component";
import { UserAddPageComponent } from "./pages/user-add-page/user-add-page.component";
import { AnalyticsResultsPageComponent } from "./pages/analytics-results-page/analytics-results-page.component";
import { AnalyticsNewGithubPageComponent } from "./pages/analytics-new-github-page/analytics-new-github-page.component";
import { AnalyticsNewTwitterPageComponent } from "./pages/analytics-new-twitter-page/analytics-new-twitter-page.component";
import { AnalyticsReportPageComponent } from "./pages/analytics-report-page/analytics-report-page.component";
import { AuthGuard } from "./guards/auth.guard";
import { AuthAdminGuard } from "./guards/auth.admin.guard";

const appRoutes: Routes = [
  { path: '', redirectTo: 'analytics', pathMatch: 'full' },
  { path: 'login', component: LoginPageComponent },
  { path: 'profile', component: ProfilePageComponent, canActivate: [AuthGuard] },
  { path: 'users', component: UsersPageComponent, canActivate: [AuthAdminGuard] },
  { path: 'users/new', component: UserAddPageComponent, canActivate: [AuthAdminGuard] },
  { path: 'users/:id', component: UserEditPageComponent, canActivate: [AuthAdminGuard] },
  { path: 'analytics', component: AnalyticsResultsPageComponent, canActivate: [AuthGuard] },
  { path: 'analytics/report/:id', component: AnalyticsReportPageComponent, canActivate: [AuthGuard] },
  { path: 'analytics/new/github', component: AnalyticsNewGithubPageComponent, canActivate: [AuthGuard] },
  { path: 'analytics/new/twitter', component: AnalyticsNewTwitterPageComponent, canActivate: [AuthGuard] },
  // { path: 'analytics/new/linkedin', component: AnalyticsNewLinkedinPageComponent },
  // { path: 'social-auth', component: SocialLoginPageComponent },
  { path: '404', component: NotFoundPageComponent, canActivate: [AuthGuard] },
  { path: '**', redirectTo: '404' }
];

export const Routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
