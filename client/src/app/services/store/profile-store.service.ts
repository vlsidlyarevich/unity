import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import { ProfileService } from '../profile.service';
import { UserSocial } from '../../models/user-social.model';
import { User } from '../../models/user.model';
import { ImageService } from '../image.service';
import { HttpErrorResponse } from "@angular/common/http";
import { NotificationService } from "../notification.service";
import { Router } from "@angular/router";
import { LoaderService } from "../loader.service";
import { Observable } from "rxjs/Observable";
import { BehaviorSubject } from "rxjs/BehaviorSubject";

@Injectable()
export class ProfileStoreService {

  private user: BehaviorSubject<User>;
  private userSocial: BehaviorSubject<UserSocial>;

  constructor(private profileService: ProfileService,
              private imageService: ImageService,
              private notificationService: NotificationService,
              private loaderService: LoaderService,
              private router: Router) {
    this.user = new BehaviorSubject<User>(this.profileService.getUserInfo());
    this.userSocial = new BehaviorSubject<UserSocial>(this.profileService.getUserSocialInfo() || new UserSocial());
  }

  getUser(): Observable<User> {
    return this.user.asObservable();
  }

  getUserSocial(): Observable<UserSocial> {
    return this.userSocial.asObservable();
  }

  updateUser(user: User) {
    this.profileService.updateUserInfo(user)
      .subscribe(
        result => {
          if (result === true) {
            this.loaderService.hide();
            this.router.navigate(['/login']);
            this.notificationService.success('User information successfully updated');
          } else {
            this.loaderService.hide();
            this.notificationService.error('User information was not updated')
          }
        }, (error: HttpErrorResponse) => {
          if (error.error instanceof Error) {
            console.log('Client-side error occured.');
          } else {
            this.notificationService.error(error.error.message);
          }
          this.loaderService.hide();
        });
  }

  updateUserSocial(userSocial: UserSocial) {
    this.profileService.updateUserSocialInfo(userSocial)
      .subscribe(
        result => {
          if (result === true) {
            this.loaderService.hide();
            this.userSocial.next(this.profileService.getUserSocialInfo() || new UserSocial());
            this.notificationService.success('User social information successfully updated');
          } else {
            this.loaderService.hide();
            this.notificationService.error('User social information was not updated');
          }
        }, (error: HttpErrorResponse) => {
          if (error.error instanceof Error) {
            console.log('Client-side error occured.');
          } else {
            this.notificationService.error(error.error.message);
          }
          this.loaderService.hide();
        });
  }

  updateUserImage(imageId: string) {
    const userSocial = this.profileService.getUserSocialInfo() || new UserSocial();
    userSocial.image = imageId;

    this.profileService.updateUserSocialInfo(userSocial)
      .subscribe(
        result => {
          if (result === true) {
            this.loaderService.hide();
            this.userSocial.next(this.profileService.getUserSocialInfo() || new UserSocial());
            if(imageId){
              this.notificationService.success('User image is updated');
            } else {
              this.notificationService.warning('User image is deleted');
            }
          } else {
            this.loaderService.hide();
            this.notificationService.error('User image was not updated');
          }
        }, (error: HttpErrorResponse) => {
          if (error.error instanceof Error) {
            console.log('Client-side error occured.');
          } else {
            this.notificationService.error(error.error.message);
          }
          this.loaderService.hide();
        });
  }
}
