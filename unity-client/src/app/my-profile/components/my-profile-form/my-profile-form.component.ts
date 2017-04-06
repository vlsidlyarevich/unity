import { Component, OnInit } from '@angular/core';
import { UserData } from '../../../models/userData';
import { ProfileService } from "../../../services/ProfileService";

@Component({
  selector: 'app-my-profile-form',
  templateUrl: './my-profile-form.component.html',
  styleUrls: ['./my-profile-form.component.css']
})
export class MyProfileFormComponent implements OnInit {
  profile: UserData;
  loading = false;
  error = '';

  constructor(private profileService: ProfileService) {
  }

  ngOnInit() {
    this.profileService.getUserData().subscribe(
      result => {
        if (result) {
          this.profile = result;
        } else {
          this.error = "Unable to get profile data";
        }
      },
      error => {
        this.error = 'Unable to get profile data';
      });
  }




}
