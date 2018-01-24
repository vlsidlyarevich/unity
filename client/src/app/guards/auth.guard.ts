import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { ProfileService } from "../services/profile.service";
import { Role } from "../models/role.model";

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private router: Router,
              private authenticationService: AuthenticationService,
              private profileService: ProfileService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.authenticationService.isLoggedIn()) {
      return true;
    } else {
      this.router.navigate([this.router.url]);
    }
  }

  canActivateAdmin(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.authenticationService.isLoggedIn()
      && this.profileService.getUserInfo().authorities.some(Role.ROLE_ADMIN)) {
      return true;
    } else {
      this.router.navigate([this.router.url]);
    }
  }
}
