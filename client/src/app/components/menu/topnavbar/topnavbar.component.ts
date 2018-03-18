import { Component, OnInit } from '@angular/core';
import { ProfileService } from '../../../services/profile.service';
import { UserSocial } from '../../../models/user-social.model';
import { ImageService } from '../../../services/image.service';
import { config } from '../../../config/config';
import { ProfileStoreService } from '../../../services/store/profile-store.service';
import { AuthenticationService } from "../../../services/authentication.service";
import { LoaderService } from "../../../services/loader.service";
import { NotificationService } from "../../../services/notification.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-topnav-bar',
  templateUrl: './topnavbar.component.html',
  styleUrls: ['./topnavbar.component.css']
})
export class TopnavbarComponent implements OnInit {

  public imageToShow: any;
  public userSocial: UserSocial;
  private imageId;
  private isImageLoading = true;

  constructor(private profileService: ProfileService,
              private authenticationService: AuthenticationService,
              private loaderService: LoaderService,
              private notificationService: NotificationService,
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

  public logOut(): void {
    this.loaderService.show();
    this.authenticationService.logout();
    this.router.navigate(['login']);
    this.notificationService.success('Successfully logged out');
    this.loaderService.hide();
  }

  private getImageFromService() {
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
}
