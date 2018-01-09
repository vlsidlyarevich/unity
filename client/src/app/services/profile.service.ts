import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { AuthenticationService } from './authentication.service';
import { User } from '../models/user.model';
import { LocalStorageService } from 'ng2-webstorage';
import { Observable } from 'rxjs/Observable';
import { UserSocial } from '../models/user-social.model';
import { config } from "../config/config";

@Injectable()
export class ProfileService {

  constructor(private authService: AuthenticationService,
              private http: HttpClient,
              private $localStorage: LocalStorageService,
              private router: Router) {
  }

  getUserInfoById(id: string) {
    const options = this.authService.createAuthOptions();

    return this.http
      .get<User>(config.userApi + '/' + id, options)
      .subscribe((response) => {
          return response;
        },
        err => {
          this.handleError(err);
        });
  }

  getUserInfo() {
    try {
      return this.$localStorage.retrieve('user');
    } catch (Error) {
      alert(Error.message);
    }
  }

  getUserSocialInfo() {
    try {
      return this.$localStorage.retrieve('userSocial');
    } catch (Error) {
      alert(Error.message);
    }
  }

  updateUserInfo(user: User) {
    const options = this.authService.createAuthOptions();
    const id = this.getUserInfo().id;

    return this.http
      .put<User>(config.userApi + '/' + id, user, options)
      .map((response) => {
          this.authService.logout();
          return true;
        },
        err => {
          this.handleError(err);
        });
  }

  updateUserSocialInfo(userSocial: UserSocial) {
    const options = this.authService.createAuthOptions();
    const url = config.userSocialApi.replace('${userId}', this.getUserInfo().id);

    return this.http
      .put<UserSocial>(url, userSocial, options)
      .map((response) => {
          this.$localStorage.store('userSocial', response);
          return true;
        },
        err => {
          this.handleError(err);
        });
  }

  updateUserImage(imageId: string) {
    const options = this.authService.createAuthOptions();
    const url = config.userSocialApi.replace('${userId}', this.getUserInfo().id);
    const userSocial = this.getUserSocialInfo();
    userSocial.image = imageId;

    return this.http
      .put<UserSocial>(url, userSocial, options)
      .map((response) => {
          this.$localStorage.store('userSocial', response);
          return true;
        },
        err => {
          this.handleError(err);
        });
  }

  updateUserInfoById(user: User, id: string) {
    const options = this.authService.createAuthOptions();

    return this.http
      .post<User>(config.userApi + '/' + id, user, options)
      .map((response) => {
          return response;
        },
        err => {
          this.handleError(err);
        });
  }

  updateUserSocialInfoById(userSocial: UserSocial, id: string, userId: string) {
    const options = this.authService.createAuthOptions();
    const url = config.userSocialApi.replace('${userId}', userId);

    return this.http
      .post<UserSocial>(url + '/' + id, userSocial, options)
      .map((response) => {
          return response;
        },
        err => {
          this.handleError(err);
        });
  }

  private handleError(err: HttpErrorResponse) {
    // in a real world app, we may send the server to some remote logging infrastructure
    // instead of just logging it to the console
    let errorMessage = '';
    if (err.error instanceof Error) {
      // A client-side or network error occurred. Handle it accordingly.
      errorMessage = `An error occurred: ${err.error.message}`;
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      errorMessage = `Server returned code: ${err.status}, error message is: ${err.message}`;
    }
    console.error(errorMessage);
    return Observable.throw(errorMessage);
  }
}
