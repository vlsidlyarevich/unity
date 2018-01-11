import { Component, OnInit } from '@angular/core';
import { ProfileService } from '../../../services/profile.service';
import { UserSocial } from '../../../models/user-social.model';
import { ImageService } from '../../../services/image.service';
import { Dispatcher } from '../../../services/dispatcher.service';
import { config } from '../../../config/config';

@Component({
  selector: 'app-topnav-bar',
  templateUrl: './topnavbar.component.html',
  styleUrls: ['./topnavbar.component.css']
})
export class TopnavbarComponent implements OnInit {

  private imageId;
  private imageToShow: any;
  private isImageLoading = true;
  private userSocial: UserSocial;

  constructor(private profileService: ProfileService,
              private imageService: ImageService) {
    Dispatcher.getInstance().userImage.subscribe((userImage: string) => {
      this.imageId = userImage;
      this.getImageFromService();
    });

    Dispatcher.getInstance().userSocial.subscribe((userSocial: any) => {
      this.userSocial = userSocial;
    });
  }

  ngOnInit() {

  }

  public getUserImageUrl(): string {
    if (this.imageId) {
      return config.imageApi + '/' + this.imageId;
    } else {
      return null;
    }
  }

  getImageFromService() {
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
