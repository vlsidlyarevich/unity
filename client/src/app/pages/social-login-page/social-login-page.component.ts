import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../services/authentication.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { NotificationService } from "../../services/notification.service";

@Component({
  selector: 'app-social-login-page',
  templateUrl: './social-login-page.component.html',
  styleUrls: ['./social-login-page.component.css']
})
export class SocialLoginPageComponent implements OnInit {
  success: boolean;
  provider: string;
  error: boolean;

  constructor(private authenticationService: AuthenticationService,
              private notificationService: NotificationService,
              private cookieService: CookieService,
              private router: Router,
              private route: ActivatedRoute,) {
  }

  ngOnInit() {
    this.route.queryParams.subscribe((queryParams) => {
      this.success = queryParams['success'];
      this.provider = queryParams['provider'];
    });

    const token = this.cookieService.get('social-authentication');
    if (token.length && this.success) {
      this.authenticationService.socialLoginWithToken(token, this.provider, false).then(() => {
        this.cookieService.delete('social-authentication');
        this.router.navigate(['']);
      }, () => {
        this.notificationService.error('Social authentication is failed');
        this.router.navigate(['login']);
      });
    } else {
      this.notificationService.error('Social authentication is failed');
      this.router.navigate(['login']);
    }
  }
}
