import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { AuthenticationService } from './authentication.service';
import { User } from '../models/user.model';
import { LocalStorageService } from 'ngx-webstorage';
import { Observable } from 'rxjs/Observable';
import { UserSocial } from '../models/user-social.model';
import { config } from '../config/config';

@Injectable()
export class ProfileService {

  constructor(private authService: AuthenticationService,
              private http: HttpClient,
              private $localStorage: LocalStorageService) {
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
    const id = this.getUserInfo().id;

    return this.http
      .put<User>(config.userApi + '/' + id, user)
      .map((response) => {
          this.authService.logout();
          return true;
        },
        err => {
          this.handleError(err);
        });
  }

  updateUserSocialInfo(userSocial: UserSocial) {
    const url = config.userSocialApi.replace('${userId}', this.getUserInfo().id);

    return this.http
      .put<UserSocial>(url, userSocial)
      .map((response) => {
          this.$localStorage.store('userSocial', response);
          return true;
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
