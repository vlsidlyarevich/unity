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
