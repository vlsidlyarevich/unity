import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from "../../services/authentication.service";
import { Router } from "@angular/router";
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-social-login-page',
  templateUrl: './social-login-page.component.html',
  styleUrls: ['./social-login-page.component.css']
})
export class SocialLoginPageComponent implements OnInit {

  constructor(private authenticationService: AuthenticationService,
              private cookieService: CookieService,
              private router: Router) {
  }

  ngOnInit() {
    const token = this.cookieService.get('social-authentication');
    if (token.length) {
      this.authenticationService.loginWithToken(token, false).then(() => {
        this.cookieService.delete('social-authentication');
        this.router.navigate(['']);
      }, () => {
        this.router.navigate(['login'], { queryParams: { 'socialLoginSuccess': 'false' } });
      });
    }
  }
}
