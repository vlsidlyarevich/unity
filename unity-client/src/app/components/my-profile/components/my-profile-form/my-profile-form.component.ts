import { Component, OnInit } from "@angular/core";
import { User } from "../../../../models/user";
import { UserSocialService } from "../../../../services/UserSocialService";
import { UserService } from "../../../../services/UserService";
import { AuthenticationService } from "../../../../services/AuthenticationService";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { UserSocial } from "../../../../models/userSocial";

@Component({
  selector: 'app-my-profile-form',
  templateUrl: './my-profile-form.component.html',
  styleUrls: ['./my-profile-form.component.css']
})
export class MyProfileFormComponent implements OnInit {
  user: User;
  accountForm: FormGroup;
  additionalForm: FormGroup;
  loading: boolean;
  accountError: string;
  additionalError: string;
  message: string;

  constructor(private userSocialService: UserSocialService, private userService: UserService,
              private authenticationService: AuthenticationService, private formBuilder: FormBuilder) {
    this.loading = false;
    this.accountError = '';
    this.additionalError = '';
    this.message = '';
  }

  ngOnInit() {
    this.initForms();
    this.fulfillAccountForm();
    this.fulfillAdditionalForm();
  }

  private initForms(): void {
    this.accountForm = this.formBuilder.group({
      username: '',
      password: ''
    });
    this.additionalForm = this.formBuilder.group({
      firstName: '',
      lastName: '',
      email: '',
      skype: '',
      additional: ''
    });
  }

  private fulfillAccountForm(): void {
    this.userService.getCurrentUser().subscribe(
      result => {
        this.accountForm = this.formBuilder.group({
          username: [result.username, [Validators.required, Validators.minLength(4)]],
          password: [result.password, [Validators.required,
            Validators.pattern('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{5,}$')]],
          enabled: { value: result.enabled, disabled: true }
        });
        this.user = result;
      },
      error => {
        this.accountError = 'Unable to get information about user: ' + error;
      });
  }

  private fulfillAdditionalForm(): void {
    this.userSocialService.getUserData().subscribe(
      result => {
        this.additionalForm = this.formBuilder.group({
          firstName: result.firstName,
          lastName: result.lastName,
          email: result.email,
          skype: result.skype,
          additional: result.additional
        });
      },
      error => {
        this.additionalError = 'Unable to get user social information: ' + error;
      });
  }

  onAccountFormSubmit() {
    this.loading = true;
    const user = new User(this.user.id, this.user.authorities,
      this.accountForm.value.username, this.accountForm.value.password, this.user.accountNonExpired,
      this.user.accountNonLocked, this.user.credentialsNonExpired, this.user.enabled,
      this.user.createdAt, this.user.updatedAt);

    this.userService.updateUserData(user)
      .subscribe(result => {
          if (result === true) {
            this.message = 'User information successfully updated, please, login again';
            this.authenticationService.logout();
          } else {
            this.accountError = 'Unable to update user information';
            this.loading = false;
          }
        },
        error => {
          this.accountError = 'Unable to register a new user: ' + error;
          this.loading = false;
        });
  }

  cancelAccount() {
    this.fulfillAccountForm();
  }

  onAdditionalInfoFormSubmit() {
    this.loading = true;
    const userData = new UserSocial(this.additionalForm.value.firstName,
      this.additionalForm.value.lastName, this.additionalForm.value.email, this.additionalForm.value.skype, this.additionalForm.value.image,
      this.additionalForm.value.additional);

    this.userSocialService.updateUserData(userData)
      .subscribe(result => {
          if (result === true) {
            this.message = 'User social information successfully updated';
          } else {
            this.additionalError = 'Unable to update user social information';
          }
          this.loading = false;
        },
        error => {
          this.additionalError = 'Unable to update user social information: ' + error;
          this.loading = false;
        });
  }

  cancelAdditional() {
    this.fulfillAdditionalForm()
  }

  showPassword(input: any): any {
    input.type = input.type === 'password' ? 'text' : 'password';
  }

  getUserAuthority(authorities: string[]): string {
    let result = 'User';
    if (authorities && authorities.find(authority => (authority === `ROLE_ADMIN`))) {
      result = 'Administrator';
    }

    return result;
  }

  private passwordMatchValidator(g: FormGroup) {
    return g.get('password').value === g.get('passwordConfirm').value
      ? null : { 'mismatch': true };
  }
}
