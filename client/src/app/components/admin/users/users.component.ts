import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { User } from "../../../models/user.model";
import { UserService } from "../../../services/user.service";
import { HttpErrorResponse } from "@angular/common/http";
import { NotificationService } from "../../../services/notification.service";
import { LoaderService } from "../../../services/loader.service";
import { ProfileService } from "../../../services/profile.service";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  public users: User[];

  @Output() openDeleteDialog = new EventEmitter<boolean>();
  @Output() closeDeleteDialog = new EventEmitter<boolean>();

  public showDeleteDialog: boolean = false;
  public showInfoDialog: boolean = false;
  public userToDelete: User;
  public userToShow: User;

  public sortKey: string = 'username';
  public reverse: boolean = false;

  constructor(private userService: UserService,
              private profileService: ProfileService,
              private notificationService: NotificationService,
              private loaderService: LoaderService) {
  }

  ngOnInit(): void {
    this.initUsers();
  }

  private initUsers() {
    this.loaderService.show();
    this.userService.getAllUsers().subscribe(
      response => {
        this.users = response.filter(user => user.id !== this.profileService.getUserInfo().id);
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

  public showUserInfoDialog(user: User) {
    this.userToShow = user;
    this.showInfoDialog = true;
  }

  public showUserDeleteDialog(user: User) {
    this.userToDelete = user;
    this.showDeleteDialog = true;
    this.openDeleteDialog.emit();
  }

  public deleteUser(event) {
    this.userService.deleteUserInfoById(this.userToDelete.id).subscribe(
      response => {
        if (response) {
          this.notificationService.success('User ' + response + ' is successfully deleted');
          this.initUsers();
        }
      }, (error: HttpErrorResponse) => {
        if (error.error instanceof Error) {
          console.log('Client-side error occured.');
        } else {
          this.notificationService.error(error.error.message);
        }
      });
  }
}
