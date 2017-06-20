import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable()
export class AuthGuard implements CanActivate {

  private router: Router;

  constructor(router: Router) {
    this.router = router;
  }

  canActivate() {
    if (localStorage.getItem('currentUser')) {
      return true;
    }

    this.router.navigate(['/auth']);
    return false;
  }
}
