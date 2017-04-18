import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { UserSocialService } from "../../../../services/UserSocialService";
import { UserService } from "../../../../services/UserService";
import { AuthenticationService } from "../../../../services/AuthenticationService";
import { UserSocial } from "../../../../models/userSocial";
import { User } from "../../../../models/user";

@Component({
  selector: 'app-my-profile-edit',
  templateUrl: './my-profile-edit.component.html',
  styleUrls: ['./my-profile-edit.component.css']
})
export class MyProfileEditComponent implements OnInit {
  userSocialData: FormGroup;
  userCredentials: FormGroup;
  loading = false;
  error = '';
  message = '';

  constructor(private userSocialService: UserSocialService, private userService: UserService,
              private authenticationService: AuthenticationService,) { }

  ngOnInit() {
  }

  showPassword(input: any): any {
    input.type = input.type === 'password' ? 'text' : 'password';
  }

  onSubmitSocialData() {
    this.loading = true;
    const userData = new UserSocial(this.userSocialData.value.firstName,
      this.userSocialData.value.lastName, this.userSocialData.value.email, this.userSocialData.value.skype, this.userSocialData.value.image,
      this.userSocialData.value.additional);

    this.userSocialService.updateUserData(userData)
      .subscribe(result => {
          if (result === true) {
            this.message = 'User social information successfully updated';
          } else {
            this.error = 'Unable to update user social information';
          }
          this.loading = false;
        },
        error => {
          this.error = 'Unable to update user social information: ' + error;
          this.loading = false;
        });
  }

  onSubmitData() {
    this.loading = true;
    const user = new User(this.userCredentials.value.id, this.userCredentials.value.authorities,
      this.userCredentials.value.username, this.userCredentials.value.password, this.userCredentials.value.accountNonExpired,
      this.userCredentials.value.accountNonLocked, this.userCredentials.value.credentialsNonExpired, this.userCredentials.value.isEnabled);

    this.userService.updateUserData(user)
      .subscribe(result => {
          if (result === true) {
            this.message = 'User information successfully updated, please, login again';
            this.authenticationService.logout();
          } else {
            this.error = 'Unable to update user information';
            this.loading = false;
          }
        },
        error => {
          this.error = 'Unable to register a new user: ' + error;
          this.loading = false;
        });
  }

  private fulfillSocialForm(userSocial: UserSocial): void {
    this.userSocialData = new FormGroup({
      firstName: new FormControl(userSocial.firstName),
      lastName: new FormControl(userSocial.lastName),
      email: new FormControl(userSocial.email),
      skype: new FormControl(userSocial.skype),
      additional: new FormControl(userSocial.additional)
    });
  }

  private fulfillSocialFormEmpty(): void {
    this.userSocialData = new FormGroup({
      firstName: new FormControl(''),
      lastName: new FormControl(''),
      email: new FormControl(''),
      skype: new FormControl(''),
      additional: new FormControl('')
    });
  }

  private fulfillCredentialsForm(user: User) {
    this.userCredentials = new FormGroup({
      id: new FormControl(user.id),
      username: new FormControl(user.username, [Validators.required, Validators.minLength(4)]),
      password: new FormControl(user.password, [Validators.required,
        Validators.pattern('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{5,}$')]),
      passwordConfirm: new FormControl(''),
      authorities: new FormControl(user.authorities),
      accountNonExpired: new FormControl(user.accountNonExpired),
      accountNonLocked: new FormControl(user.accountNonLocked),
      credentialsNonExpired: new FormControl(user.credentialsNonExpired),
      isEnabled: new FormControl(user.enabled)
    }, this.passwordMatchValidator)
  }

  private passwordMatchValidator(g: FormGroup) {
    return g.get('password').value === g.get('passwordConfirm').value
      ? null : { 'mismatch': true };
  }
}
