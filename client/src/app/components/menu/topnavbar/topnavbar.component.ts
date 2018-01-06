import { Component, OnInit } from '@angular/core';
import { ProfileService } from '../../../services/profile.service';
import { UserSocial } from '../../../models/user-social.model';

@Component({
  selector: 'app-topnav-bar',
  templateUrl: './topnavbar.component.html',
  styleUrls: ['./topnavbar.component.css']
})
export class TopnavbarComponent implements OnInit {

  private userSocial: UserSocial;

  constructor(private profileService: ProfileService) {
  }

  ngOnInit() {
    this.userSocial = this.profileService.getUserSocialInfo();
  }
}
