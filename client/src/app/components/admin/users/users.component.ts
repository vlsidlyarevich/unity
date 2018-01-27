import { Component } from '@angular/core';
import { User } from "../../../models/user.model";
import { UserService } from "../../../services/user.service";
import { HttpErrorResponse } from "@angular/common/http";
import { NotificationService } from "../../../services/notification.service";
import { LoaderService } from "../../../services/loader.service";
import { Role } from "../../../models/role.model";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent {

  public users: User[];

  public username: string;
  public role: Role;
  public enabled: boolean;
  public sortKey: string = 'username';
  public reverse: boolean = false;

  constructor(private userService: UserService,
              private notificationService: NotificationService,
              private loaderService: LoaderService) {
    this.loaderService.show();
    this.userService.getAllUsers().subscribe(
      response => {
        this.users = response;
        this.users.forEach(user => {
          user.authorities.sort();
        });
        this.loaderService.hide();
      }, (error: HttpErrorResponse) => {
        if (error.error instanceof Error) {
          console.log('Client-side error occured.');
        } else {
          this.notificationService.error(error.error.message);
        }
        this.loaderService.hide();
      });
  }

  public sort(sortKey) {
    this.sortKey = sortKey;
    this.reverse = !this.reverse;
  }
}
