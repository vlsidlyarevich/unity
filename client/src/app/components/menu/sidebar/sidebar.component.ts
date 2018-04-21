import { Component, OnInit, Renderer2 } from '@angular/core';
import { UserSocial } from "../../../models/user-social.model";
import { ProfileService } from "../../../services/profile.service";
import { ProfileStoreService } from "../../../services/store/profile-store.service";
import { config } from "../../../config/config";
import { ImageService } from "../../../services/image.service";
import { Router } from "@angular/router";
import { animate, group, state, style, transition, trigger } from "@angular/animations";
import { AuthenticationService } from "../../../services/authentication.service";
import { Role } from "../../../models/role.model";
import { NotificationService } from "../../../services/notification.service";
import { LoaderService } from "../../../services/loader.service";

@Component({
  selector: 'app-side-bar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css'],
  animations: [
    trigger('slideInOut', [
      state('in', style({
        'max-height': '500px', 'opacity': '1', 'visibility': 'visible'
      })),
      state('out', style({
        'max-height': '0px', 'opacity': '0', 'visibility': 'hidden'
      })),
      transition('in => out', [group([
          animate('400ms ease-in-out', style({
            'opacity': '0'
          })),
          animate('600ms ease-in-out', style({
            'max-height': '0px'
          })),
          animate('700ms ease-in-out', style({
            'visibility': 'hidden'
          }))
        ]
      )]),
      transition('out => in', [group([
          animate('1ms ease-in-out', style({
            'visibility': 'visible'
          })),
          animate('600ms ease-in-out', style({
            'max-height': '500px'
          })),
          animate('800ms ease-in-out', style({
            'opacity': '1'
          }))
        ]
      )])
    ]),
  ]
})

export class SidebarComponent implements OnInit {

  public adminRouteGroupExpanded: boolean = false;
  public analyticsRouteGroupExpanded: boolean = false;

  public imageToShow: any;
  public userSocial: UserSocial;
  private imageId;
  private isImageLoading = true;

  constructor(private profileService: ProfileService,
              private authenticationService: AuthenticationService,
              private renderer: Renderer2,
              private router: Router,
              private imageService: ImageService,
              private profileStoreService: ProfileStoreService,
              private loaderService: LoaderService,
              private notificationService: NotificationService) {
  }

  ngOnInit() {
    this.profileStoreService.getUserSocial().subscribe((userSocial: any) => {
      this.userSocial = userSocial;
      this.imageId = userSocial.image;
      this.getImageFromService();
    });

    this.initMenu();
  }

  public getUserImageUrl(): string {
    if (this.imageId) {
      return config.imageApi + '/' + this.imageId;
    } else {
      return null;
    }
  }

  public getImageFromService() {
    if (this.userSocial.image) {
      this.isImageLoading = true;
      this.imageService.getImage(this.getUserImageUrl())
        .subscribe(data => {
          this.imageToShow = data;
          this.isImageLoading = false;
        }, error => {
          this.isImageLoading = false;
          console.log(error);
        });
    }
  }

  public isAdmin(): boolean {
    return this.profileService.getUserInfo().authorities
      .find(authority => (authority == Role.ROLE_ADMIN));
  }

  public toggleAnalyticsMenu() {
    if (this.adminRouteGroupExpanded) {
      this.adminRouteGroupExpanded = false;
    }
    this.analyticsRouteGroupExpanded = !this.analyticsRouteGroupExpanded;
  }

  public toggleAdminMenu() {
    if (this.analyticsRouteGroupExpanded) {
      this.analyticsRouteGroupExpanded = false;
    }
    this.adminRouteGroupExpanded = !this.adminRouteGroupExpanded;
  }

  private initMenu() {
    if (this.router.url === '/users' || this.router.url === '/users/new') {
      this.adminRouteGroupExpanded = true;
    } else if (this.router.url === '/analytics/new/github' || this.router.url === '/analytics/new/twitter') {
      this.analyticsRouteGroupExpanded = true;
    }
  }

  public logOut(): void {
    this.loaderService.show();
    this.authenticationService.logout();
    this.router.navigate(['login']);
    this.notificationService.success('Successfully logged out');
    this.loaderService.hide();
  }
}
