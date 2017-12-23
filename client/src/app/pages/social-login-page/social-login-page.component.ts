import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../services/authentication.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-social-login-page',
  templateUrl: './social-login-page.component.html',
  styleUrls: ['./social-login-page.component.css']
})
export class SocialLoginPageComponent implements OnInit {
  success: boolean;
  error: boolean;

  constructor(private authenticationService: AuthenticationService,
              private cookieService: CookieService,
              private router: Router,
              private route: ActivatedRoute, ) {
  }

  ngOnInit() {
    this.route.queryParams.subscribe((queryParams) => {
      this.success = queryParams['success'];
    });

    console.log(this.success);

    const token = this.cookieService.get('social-authentication');
    if (token.length && this.success) {
      this.authenticationService.loginWithToken(token, false).then(() => {
        this.cookieService.delete('social-authentication');
        this.router.navigate(['']);
      }, () => {
        this.router.navigate(['login'], { queryParams: { 'socialLoginSuccess': 'false' } });
      });
    } else {
      this.router.navigate(['login'], { queryParams: { 'socialLoginSuccess': 'false' } });
    }
  }
}
