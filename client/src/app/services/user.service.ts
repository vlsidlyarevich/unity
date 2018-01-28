import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { config } from "../config/config";
import { User } from "../models/user.model";
import { UserSocial } from "../models/user-social.model";
import { Observable } from "rxjs/Observable";

@Injectable()
export class UserService {

  constructor(private http: HttpClient) {
  }

  getAllUsers() {
    return this.http
      .get<User[]>(config.userApi)
      .map((response) => {
        return response;
      });
  }

  getUserInfoById(id: string) {
    return this.http
      .get<User>(config.userApi + '/' + id)
      .map((response) => {
          return response;
        },
        err => {
          this.handleError(err);
        });
  }

  getUserSocialInfoById(id: string) {
    return this.http
      .get<User>(config.userApi + '/' + id)
      .map((response) => {
          return response;
        },
        err => {
          this.handleError(err);
        });
  }

  updateUserInfoById(user: User, id: string) {
    return this.http
      .post<User>(config.userApi + '/' + id, user)
      .map((response) => {
          return response;
        },
        err => {
          this.handleError(err);
        });
  }

  updateUserSocialInfoById(userSocial: UserSocial, id: string, userId: string) {
    const url = config.userSocialApi.replace('${userId}', userId);

    return this.http
      .post<UserSocial>(url + '/' + id, userSocial)
      .map((response) => {
          return response;
        },
        err => {
          this.handleError(err);
        });
  }

  deleteUserInfoById(id: string) {
    return this.http
      .delete(config.userApi + '/' + id)
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
