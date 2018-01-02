import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user.model';
import { UserSocial } from '../../models/user-social.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProfileService } from '../../services/profile.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  private userSocial: UserSocial;
  private image: any;
  private user: User;
  private formBuilder: FormBuilder;
  private userDataForm: FormGroup;
  private userSocialDataForm: FormGroup;

  constructor(private profileService: ProfileService,
              private router: Router) {
    this.formBuilder = new FormBuilder();
  }

  ngOnInit() {
    this.user = this.profileService.getUserInfo();
    this.userSocial = this.profileService.getUserSocialInfo() || new UserSocial();
    this.initializeForms();
  }

  private initializeForms() {
    this.initializeUserForm();
    this.initializeUserSocialForm();
  }

  public updateUserData() {
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

    this.profileService.updateUserInfo(user)
      .subscribe(
        result => {
          if (result === true) {
            this.router.navigate(['/login']);
          } else {
            //??
          }
        },
        error => {
          //??
        });
  }

  public updateUserSocialData() {
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
      image: this.image || '',
    };

    this.profileService.updateUserSocialInfo(userSocial)
      .subscribe(
        result => {
          if (result === true) {
            this.userSocial = this.profileService.getUserSocialInfo() || new UserSocial();
            this.initializeUserSocialForm();
          } else {
            //??
          }
        },
        error => {
          //??
        });
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
      email: [this.userSocial.email, [Validators.required, Validators.email]],
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
}
