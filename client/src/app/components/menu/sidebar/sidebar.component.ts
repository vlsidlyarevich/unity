import { Component, ElementRef, OnInit, Renderer2, ViewChild } from '@angular/core';
import { UserSocial } from "../../../models/user-social.model";
import { ProfileService } from "../../../services/profile.service";
import { ProfileStoreService } from "../../../services/store/profile-store.service";
import { config } from "../../../config/config";
import { ImageService } from "../../../services/image.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-side-bar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  @ViewChild("menu")
  menu: ElementRef;

  public imageToShow: any;
  public userSocial: UserSocial;
  private imageId;
  private isImageLoading = true;

  constructor(private profileService: ProfileService,
              private renderer: Renderer2,
              private router: Router,
              private imageService: ImageService,
              private profileStoreService: ProfileStoreService) {
  }

  ngOnInit() {
    this.profileStoreService.getUserSocial().subscribe((userSocial: any) => {
      this.userSocial = userSocial;
      this.imageId = userSocial.image;
      this.getImageFromService();
    });
  }

  public getUserImageUrl(): string {
    if (this.imageId) {
      return config.imageApi + '/' + this.imageId;
    } else {
      return null;
    }
  }

  getImageFromService() {
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

  public currentRouteClass(routeGroup: string): string {
    if (routeGroup === 'admin') {
      if (this.router.url === '/users' || this.router.url === '/users/new') {
        return 'visible-menu';
      }
    } else if (routeGroup === 'analytics') {
      if (this.router.url === '/analytics/new/github' || this.router.url === '/analytics/new/twitter') {
        return 'visible-menu';
      }
    } else return '';
  }
}
