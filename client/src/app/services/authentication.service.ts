import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import { LocalStorageService, SessionStorageService } from 'ng2-webstorage';
import { User } from '../models/user.model';
import { UserSocial } from '../models/user-social.model';
import {config} from "../config/config";

@Injectable()
export class AuthenticationService {

  constructor(private $localStorage: LocalStorageService,
              private $sessionStorage: SessionStorageService,
              private http: HttpClient,
              private router: Router) {
  }

  createAuthOptions() {
    const currentToken = JSON.parse(this.$localStorage.retrieve('authenticationToken'));

    //Angular 4 bug  https://github.com/angular/angular/issues/19044
    const headerParams = {
      'Content-Type': 'application/json',
      'x-auth-token': currentToken
    };
    const headers = new HttpHeaders(headerParams);
    const params = new HttpParams();
    return ({ headers: headers, params: params });
  }

  isLoggedIn(): boolean {
    try {
      return this.$localStorage.retrieve('authenticationToken') || this.$sessionStorage.retrieve('authenticationToken');
    } catch (Error) {
      alert(Error.message);
    }
  }

  login(username: string, password: string, rememberMe: boolean) {
    const body = JSON.stringify({ username: username, password: password });
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http
      .post<JwtResponse>(config.authApi, body, { headers: headers, observe: 'response' })
      .map((data) => {
        if (data && data.body.token) {
          try {
            this.storeAuthenticationToken(JSON.stringify(data.body.token), rememberMe);
            this.storeCurrentUser();
            this.storeCurrentUserSocial();
            return data;
          } catch (error) {
            console.log(error);
          }
        }
      });
  }

  loginWithToken(jwt, rememberMe) {
    if (jwt) {
      this.storeAuthenticationToken(jwt, rememberMe);
      return Promise.resolve(jwt);
    } else {
      return Promise.reject('Login error');
    }
  }

  private storeCurrentUser() {
    const options = this.createAuthOptions();

    this.http
      .get<User>(config.userApi + '/me', options)
      .subscribe((response) => {
          this.$localStorage.store('user', response);
        },
        err => {
          this.handleError(err);
        });
  }

  private storeCurrentUserSocial() {
    const options = this.createAuthOptions();
    const url = config.userSocialApi.replace('${userId}', this.$localStorage.retrieve('user').id);

    this.http
      .get<UserSocial>(url, options)
      .subscribe((response) => {
          this.$localStorage.store('userSocial', response);
        },
        err => {
          this.handleError(err);
        });
  }

  storeAuthenticationToken(jwt, rememberMe) {
    if (rememberMe) {
      this.$sessionStorage.store('authenticationToken', jwt);
    } else {
      this.$localStorage.store('authenticationToken', jwt);
    }
  }

  logout(): Observable<any> {
    return new Observable((observer) => {
      this.$localStorage.clear('authenticationToken');
      this.$sessionStorage.clear('authenticationToken');
      observer.complete();
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

    //FIXME
    return Observable.throw(errorMessage);
  }
}
