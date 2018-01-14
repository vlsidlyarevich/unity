import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user.model';
import { UserSocial } from '../../models/user-social.model';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { NotificationService } from '../../services/notification.service';
import { LoaderService } from "../../services/loader.service";
import { AuthenticationService } from "../../services/authentication.service";
import { config } from "../../config/config";
import { ImageService } from "../../services/image.service";
import { ProfileStoreService } from "../../services/store/profile-store.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  public userSocial: UserSocial;
  public imageToShow: any;
  public user: User;
  public userSocialDataForm: FormGroup;
  public userDataForm: FormGroup;
  private isImageLoading = true;
  private imageId: any;
  private formBuilder: FormBuilder;

  public authHeaders: { [name: string]: any };
  public apiUrl: string;

  constructor(private profileStoreService: ProfileStoreService,
              private authenticationService: AuthenticationService,
              private imageService: ImageService,
              private notificationService: NotificationService,
              private loaderService: LoaderService) {
    this.formBuilder = new FormBuilder();

    this.profileStoreService.getUser().subscribe((user: User) => {
      this.user = user;
      this.initializeUserForm();
    });

    this.profileStoreService.getUserSocial().subscribe((userSocial: UserSocial) => {
      this.userSocial = userSocial;
      this.imageId = this.userSocial.image;
      this.getImageFromService();
      this.initializeUserSocialForm();
    });

    this.authHeaders = {
      'x-auth-token': this.authenticationService.createAuthOptions().headers.get('x-auth-token')
    };

    this.apiUrl = config.imageApi;
  }

  ngOnInit() {

  }

  public updateUserData() {
    this.loaderService.show();

    const user: User = {
      id: this.userDataForm.value.id,
      authorities: this.userDataForm.value.authorities,
      username: this.userDataForm.value.username,
      password: this.userDataForm.value.password,
      linkedInLoginEnabled: this.userDataForm.value.linkedInLoginEnabled,
      twitterLoginEnabled: this.userDataForm.value.twitterLoginEnabled,
      facebookLoginEnabled: this.userDataForm.value.facebookLoginEnabled,
      accountNonExpired: this.userDataForm.value.accountNonExpired,
      accountNonLocked: this.userDataForm.value.accountNonLocked,
      credentialsNonExpired: this.userDataForm.value.credentialsNonExpired,
      enabled: this.userDataForm.value.enabled,
      createdAt: this.userDataForm.value.createdAt,
      updatedAt: this.userDataForm.value.updatedAt,
    };

    this.profileStoreService.updateUser(user);
  }

  public updateUserSocialData() {
    this.loaderService.show();

    const userSocial: UserSocial = {
      id: this.userSocialDataForm.value.id,
      userId: this.user.id,
      firstName: this.userSocialDataForm.value.firstName,
      lastName: this.userSocialDataForm.value.lastName,
      linkedIn: this.userSocialDataForm.value.linkedIn,
      twitter: this.userSocialDataForm.value.twitter,
      facebook: this.userSocialDataForm.value.facebook,
      email: this.userSocialDataForm.value.email,
      skype: this.userSocialDataForm.value.skype,
      additional: this.userSocialDataForm.value.additional,
      createdAt: this.userSocialDataForm.value.createdAt,
      updatedAt: this.userSocialDataForm.value.updatedAt,
      image: this.imageId || '',
    };

    this.profileStoreService.updateUserSocial(userSocial);
  }

  private initializeUserForm() {
    this.userDataForm = this.formBuilder.group({
      id: [this.user.id],
      username: [this.user.username, [Validators.required, Validators.minLength(4)]],
      password: [this.user.password, [Validators.required,
        Validators.pattern('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{5,}$')]],
      facebookLoginEnabled: [this.user.facebookLoginEnabled],
      linkedInLoginEnabled: [this.user.linkedInLoginEnabled],
      twitterLoginEnabled: [this.user.twitterLoginEnabled],
    });
  }

  private initializeUserSocialForm() {
    this.userSocialDataForm = this.formBuilder.group({
      email: [this.userSocial.email, [this.emailOrEmpty]],
      firstName: [this.userSocial.firstName],
      lastName: [this.userSocial.lastName],
      facebook: [this.userSocial.facebook],
      linkedIn: [this.userSocial.linkedIn],
      skype: [this.userSocial.skype],
      twitter: [this.userSocial.twitter],
      additional: [this.userSocial.additional],

      createdAt: [this.userSocial.createdAt],
      updatedAt: [this.userSocial.updatedAt],
    });
  }

  public getUserImageUrl(): string {
    if (this.imageId) {
      return config.imageApi + '/' + this.imageId;
    } else {
      return null;
    }
  }

  public showPassword(input: any): any {
    input.type = input.type === 'password' ? 'text' : 'password';
  }

  public getUserAuthority(authorities: string[]): string {
    let result = 'User';
    if (authorities && authorities.find(authority => (authority === `ROLE_ADMIN`))) {
      result = 'Administrator';
    }

    return result;
  }

  public resetUserForm() {
    this.initializeUserForm();
  }

  public resetUserSocialForm() {
    this.initializeUserSocialForm();
  }

  public emailOrEmpty(control: AbstractControl): ValidationErrors | null {
    return control.value === '' ? null : Validators.email(control);
  }

  onRemoved(event) {
    this.imageService.deleteImage(this.imageId)
      .subscribe(response => {
        this.imageId = null;
        this.notificationService.success('User image is reseted');
      });
  }

  onUploadFinished(event) {
    this.imageId = event.serverResponse._body;
    this.getImageFromService();
    this.notificationService.success('Submit user social form to update image');
  }

  getImageFromService() {
    if (this.imageId) {
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
