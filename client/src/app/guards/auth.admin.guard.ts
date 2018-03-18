import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { ProfileService } from "../services/profile.service";
import { Role } from "../models/role.model";

@Injectable()
export class AuthAdminGuard implements CanActivate {

  constructor(private router: Router,
              private authenticationService: AuthenticationService,
              private profileService: ProfileService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.authenticationService.isLoggedIn()
      && this.profileService.getUserInfo().authorities
        .find(authority => (authority == Role.ROLE_ADMIN))) {
      return true;
    } else if (this.authenticationService.isLoggedIn()) {
      this.router.navigate(['']);
    } else {
      this.router.navigate(['login']);
    }
  }
}
