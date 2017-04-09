import { Component, OnInit } from '@angular/core';
import { UserData } from '../../../models/userData';
import { ProfileService } from "../../../services/ProfileService";
import { FormControl, FormGroup, Validators } from "@angular/forms";

@Component({
  selector: 'app-my-profile-form',
  templateUrl: './my-profile-form.component.html',
  styleUrls: ['./my-profile-form.component.css']
})
export class MyProfileFormComponent implements OnInit {
  profile: FormGroup;
  loading = false;
  error = '';
  message = '';

  constructor(private profileService: ProfileService) {
  }

  ngOnInit() {
    this.profileService.getUserData().subscribe(
      result => {
        if (result) {
          this.fulfillForm(result);
        } else {
          this.error = "Unable to get profile data";
        }
      },
      error => {
        this.error = 'Unable to get profile data';
      });
  }

  showPassword(input: any): any {
    input.type = input.type === 'password' ? 'text' : 'password';
  }

  onSubmit() {
    this.loading = true;
    const userData = new UserData(this.profile.value.username, this.profile.value.password, this.profile.value.firstName,
      this.profile.value.lastName, this.profile.value.email, this.profile.value.skype, this.profile.value.image,
      this.profile.value.additional);

    this.profileService.updateUserData(userData)
      .subscribe(result => {
          if (result === true) {
            this.message = 'Profile successfully updated';
          } else {
            this.error = 'Unable to register a new user';
            this.loading = false;
          }
        },
        error => {
          this.error = 'Unable to register a new user';
          this.loading = false;
        });
  }

  private fulfillForm(userData: UserData): void {
    this.profile = new FormGroup({
      username: new FormControl(userData.username, [Validators.required, Validators.minLength(4)]),
      password: new FormControl(JSON.parse(localStorage.getItem('currentUser')).password, [Validators.required,
        Validators.pattern('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{5,}$')]),
      firstName: new FormControl(userData.firstName),
      lastName: new FormControl(userData.lastName),
      email: new FormControl(userData.email),
      skype: new FormControl(userData.skype),
      additional: new FormControl(userData.additional)
    });
  }
}
