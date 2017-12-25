import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { AuthenticationService } from './authentication.service';
import { User } from '../models/user.model';
import { LocalStorageService } from 'ng2-webstorage';
import { api } from '../app.constants';
import { Observable } from 'rxjs/Observable';

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
      .get<User>(api.user + '/' + id, options)
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
