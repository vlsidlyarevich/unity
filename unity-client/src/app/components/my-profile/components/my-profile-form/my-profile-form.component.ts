import { Component, OnInit } from "@angular/core";
import { UserSocial } from "../../../../models/userSocial";
import { User } from "../../../../models/user";
import { UserSocialService } from "../../../../services/UserSocialService";
import { UserService } from "../../../../services/UserService";
import { AuthenticationService } from "../../../../services/AuthenticationService";

@Component({
  selector: 'app-my-profile-form',
  templateUrl: './my-profile-form.component.html',
  styleUrls: ['./my-profile-form.component.css']
})
export class MyProfileFormComponent implements OnInit {
  userSocial: UserSocial = new UserSocial("", "", "", "", "", "");
  user: User = new User("", [], "", "", false, false, false, false);
  loading = false;
  //TODO:separate errors
  error = '';
  message = '';

  constructor(private userSocialService: UserSocialService, private userService: UserService,
              private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
    this.userSocialService.getUserData().subscribe(
      result => {
        this.userSocial = result;
      },
      error => {
        this.error = 'Unable to get user social information: ' + error;
      });

    this.userService.getCurrentUser().subscribe(
      result => {
        if (result) {
          this.user = result;
        } else {
          this.error = "Unable to get information about user";
        }
      },
      error => {
        this.error = 'Unable to get information about user: ' + error;
      });
  }

  showPassword(input: any): any {
    input.type = input.type === 'password' ? 'text' : 'password';
  }
}
